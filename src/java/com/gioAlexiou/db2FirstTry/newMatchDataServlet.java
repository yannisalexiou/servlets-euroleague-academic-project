package com.gioAlexiou.db2FirstTry;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author gioalexiou
 */
@WebServlet("/uploadMatchData")
@MultipartConfig
public class newMatchDataServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    Connection conn = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    String serverURL = "195.251.166.123";
    String serverPort = "1521";
    String serverSID = "orcl";
    String username = "c##icsd11138";
    String password = "icsd11138";
    
    String id;
    String round;
    String homeTeam;
    String awayTeam;
    String gameDateTime;
    String arena;
    String attendees;
    
    InputStream fileContent;
    InputStream homeTeamDataTXT;
    InputStream awayTeamDataTXT;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {   
        //Step 1: Create path components to save the file
        fileContent = null;
        
        id = request.getParameter("id");
        round = request.getParameter("round");
        homeTeam = request.getParameter("homeTeam");
        awayTeam = request.getParameter("awayTeam");
        gameDateTime = request.getParameter("gameDateTime");
        arena = request.getParameter("arena");
        attendees = request.getParameter("attendees");
        
        //Step 2: Retrieving the data from post request
        
        //a): First File
        Part homeTeamFilePart = request.getPart("homeTeamDataTXT"); // Retrieves <input type="file" name="homeTeamDataTXT">
        String homeTeamTXTFileName = Paths.get(homeTeamFilePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        
        //b) Second File
        Part awayTeamFilePart = request.getPart("awayTeamDataTXT"); // Retrieves <input type="file" name="awayTeamDataTXT">
        String awayTeamTXTFileName = Paths.get(awayTeamFilePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        
        //Print Data to console
        //readGameDataFromTXT(id, homeTeamFilePart);
        //readGameDataFromTXT(id, awayTeamFilePart);
        
        int homeTeamScore = 0;
        int awayTeamScore = 0;
        
        //Step 4: Connect to Server
        try 
        {
            insertMatchToDb();
            
            //Insert Text Files to Db
            insertGameDataToDB(homeTeamFilePart);
            insertGameDataToDB(awayTeamFilePart);
            
            homeTeamScore = teamScoreCalculation(homeTeam);
            awayTeamScore = teamScoreCalculation(awayTeam);
        } catch (SQLException ex) {
            Logger.getLogger(newTeamServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Step 5: Add retrieved data to attributes
        request.setAttribute("id", id);
        request.setAttribute("round", round);
        request.setAttribute("homeTeam", homeTeam);
        request.setAttribute("awayTeam", awayTeam);
        request.setAttribute("gameDateTime", gameDateTime);
        request.setAttribute("arena", arena);
        request.setAttribute("attendees", attendees);
        request.setAttribute("homeTeamDataTXT", homeTeamTXTFileName);
        request.setAttribute("awayTeamDataTXT", awayTeamTXTFileName);
        request.setAttribute("homeScore", homeTeamScore);
        request.setAttribute("awayScore", awayTeamScore);
        
        if (homeTeamScore > awayTeamScore)
        {
            request.setAttribute("winnerTeam", homeTeam);
        }
        else if (awayTeamScore > homeTeamScore)
        {
            request.setAttribute("winnerTeam", awayTeam);
        }
        else
        {
            //Never Called
            request.setAttribute("winnerTeam", "Draw");
        }
        
        //Step 6: Get Request dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view_new_match_data.jsp");
        
        //Step 7: Forward the request to JSP
        dispatcher.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    void connectToDB()
    {
        try 
        {
            //συνδεση στη ΒΔ.
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            String connectionURL = "jdbc:oracle:thin:@" + serverURL + ":" + serverPort + ":" + serverSID;
            conn = DriverManager.getConnection(connectionURL, username, password);
            
            System.out.println("Connected Successful");
        } 
        catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(newTeamServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex) 
        {
            Logger.getLogger(newTeamServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IllegalAccessException ex) 
        {
            Logger.getLogger(newTeamServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(newTeamServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        // τέλος συνδεση στη ΒΔ.
    }
    
    void insertMatchToDb() throws SQLException
    {
        //run insert από τα δεδομένα της φόρμας
        connectToDB();
        
        try
        {
            
            String insertNewGameQuery = "INSERT INTO GAME"
				+ "(ID, ROUND, HOMETEAM, AWAYTEAM, GAMEDATETIME, ARENA, ATTENDEES) VALUES"
				+ "(?,?,?,?,?,?,?)";

            preparedStatement = conn.prepareStatement(insertNewGameQuery);
            
            Integer idToDB = Integer.parseInt(id);
            Integer roundToDB = Integer.parseInt(round);
            Integer attendeesToDB = Integer.parseInt(attendees);
            
            //Date Time Retrieved Template 1999-12-10T22:48
            
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date date = null;
            try
            {
                date = format.parse(gameDateTime);
                System.out.println(date);
            }
            catch (ParseException ex)
            {
                Logger.getLogger(newMatchDataServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            preparedStatement.setInt(1, idToDB);
            preparedStatement.setInt(2, roundToDB);
            preparedStatement.setString(3, homeTeam);
            preparedStatement.setString(4, awayTeam);
            preparedStatement.setDate(5, new java.sql.Date(date.getTime()));
            preparedStatement.setString(6, arena);
            preparedStatement.setInt(7, attendeesToDB);

            // execute insert SQL stetement
            preparedStatement.executeUpdate();

            System.out.println("Record is inserted into GAME table!");
        }
        catch (SQLException e) 
        {

            System.out.println(e.getMessage());

        }        
        finally 
        {

            if (preparedStatement != null) 
            {
                    preparedStatement.close();
            }

            if (conn != null) {
                    conn.close();
            }

        }
        
    }
    void readGameDataFromTXT(String id, Part fileName) throws IOException
    {
        InputStream getFileContent = fileName.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getFileContent));
        
        String line;
        while ((line = bufferedReader.readLine()) != null) 
        {
            String[] mydata = line.split(";");

            for (int i = 0; i < mydata.length; i++) 
            {
                if( i==2 )
                {
                    String[] minutes = mydata[i].split(":");
                    if( !mydata[i].equals("0"))
                    {
                        int sum= Integer.parseInt(minutes[0])*60+ Integer.parseInt(minutes[1]);
                        mydata[i]= Integer.toString(sum);
                    }
                }
                System.out.println(mydata[i]);

            }
            System.out.println("Out of For");
        }
        System.out.println("Out of While");
    }
    void insertGameDataToDB(Part fileName) throws SQLException, IOException
    {
        //run insert από τα δεδομένα της φόρμας
        connectToDB();
        InputStream getFileContent = fileName.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getFileContent));
        
        try
        {
            String line;
            while ((line = bufferedReader.readLine()) != null) 
            {
                String[] mydata = line.split(";");
                
                String insertToGameData = "INSERT INTO GAMEDATA(GAMEID, PLAYERID, MININGAME, POINTS, TWOPOINTS, THREEPOINTS, FREETHROW, REBOUNDOFF, REBOUNDDEF, REBOUNDTOTAL, ASSIST, STEAL, MISTAKE, BLOCKFV, BLOCKAG, FOULCM, FOULRV, PIR) values('" 
                    + id + "',(SELECT ID FROM PLAYER WHERE NUM=? AND NAME=?),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                
                PreparedStatement ps = conn.prepareStatement(insertToGameData);
                
                for (int i = 0; i < mydata.length; i++) 
                {
                    if( i==2 )
                    {
                        String[] minutes = mydata[i].split(":");
                        if( !mydata[i].equals("0"))
                        {
                            int sum= Integer.parseInt(minutes[0])*60+ Integer.parseInt(minutes[1]);
                            mydata[i]= Integer.toString(sum);
                        }
                    }
                    ps.setString(i + 1, mydata[i]); //Insert στη σωστη σειρά
                        
                }
                
                // execute insert SQL stetement
                int executeUpdate = ps.executeUpdate();
                
                System.out.println("Record is inserted into GAMEDATA table!");
            }
        }
        catch (SQLException e) 
        {

            System.out.println(e.getMessage());

        }        
        finally 
        {

            if (preparedStatement != null) 
            {
                    preparedStatement.close();
            }

            if (conn != null) 
            {
                    conn.close();
            }

        }
        
    }
    
    int teamScoreCalculation(String team) throws SQLException
    {
        int finalScore = 0;
        ResultSet resultSet = null;
        
        connectToDB();
        
        try
        {
            //Calculating the sum from the query below
            String teamsPointQuery = "SELECT SUM(Points) FROM GAMEDATA WHERE GAMEID=\'" + id + "\' AND PLAYERID IN (SELECT ID FROM PLAYER WHERE TEAM=\'" + team + "\')";
            preparedStatement = conn.prepareStatement(teamsPointQuery);
            
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) 
            {
                //Assign the sum to finalScore Variable
                finalScore = Integer.parseInt(resultSet.getString(1));
            }
            
        }
        catch (SQLException e) 
        {

            System.out.println(e.getMessage());
            return 0;
        }        
        finally 
        {

            if (preparedStatement != null) 
            {
                    preparedStatement.close();
            }

            if (conn != null) 
            {
                    conn.close();
            }

        }
        
        return finalScore;
    }

}
