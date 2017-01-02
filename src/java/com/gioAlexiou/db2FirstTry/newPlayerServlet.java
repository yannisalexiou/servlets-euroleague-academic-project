package com.gioAlexiou.db2FirstTry;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Lob;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

@WebServlet("/uploadPlayer")
@MultipartConfig
public class newPlayerServlet extends HttpServlet {

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
    String number;
    String playerName;
    String playerTeam;
    String playerHeight;
    String playerNationality;
    String playerPosition;
    String playerBornDate;
    
    InputStream fileContent;
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet newPlayerServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet newPlayerServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        number = request.getParameter("number");
        playerName = request.getParameter("playerName");
        playerTeam = request.getParameter("playerTeam");
        playerHeight = request.getParameter("playerHeight");
        playerNationality = request.getParameter("playerNationality");
        playerPosition = request.getParameter("playerPosition");
        playerBornDate = request.getParameter("playerBordDate");
        
        //Step 2: Retrieving the data from post request
        Part filePart = request.getPart("playerPhoto"); // Retrieves <input type="file" name="playerPhoto">
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        InputStream getFileContent = filePart.getInputStream();
        
        //InputStream input = uploadedFile.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = getFileContent.read(buffer)) > -1 ) {
            baos.write(buffer, 0, len);
        }
        baos.flush();
        
        //Step 3: Saving our image local
        System.out.println("Absolute Path:" + new File(".").getAbsolutePath());
        
        final Path destination = Paths.get("/Applications/NetBeans/glassfish-4.0/glassfish/domains/domain1/config/" + fileName);
        
        try ( final InputStream in = filePart.getInputStream(); ) 
        {
            Files.copy(in, destination, StandardCopyOption.REPLACE_EXISTING); //Replace Existing file
            //Files.copy(in, destination); //without replace
        }
        
        System.out.println("Uploaded file successfully saved in " + destination);
        
        //Step 4: Connect to Server
        ConnectToDB();
        try {
            insertPlayerToDb(baos);
        } catch (SQLException ex) {
            Logger.getLogger(newTeamServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Step 5: Add retrieved data to attributes
        request.setAttribute("id", id);
        request.setAttribute("number", number);
        request.setAttribute("playerName", playerName);
        request.setAttribute("playerTeam", playerTeam);
        request.setAttribute("playerHeight", playerHeight);
        request.setAttribute("playerNationality", playerNationality);
        request.setAttribute("playerPosition", playerPosition);
        request.setAttribute("playerBordDate", playerBornDate);
        request.setAttribute("playerPhoto", fileName);
        
        //Step 6: Get Request dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view_new_player.jsp");
        
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
    
    void ConnectToDB()
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
    
    void insertPlayerToDb(ByteArrayOutputStream baos) throws SQLException
    {
        //run insert από τα δεδομένα της φόρμας
        
        try
        {
            
            String insertTableSQL = "INSERT INTO PLAYER"
				+ "(ID, NUM, NAME, PHOTO, TEAM, HEIGHT, NATIONALITY, POSITION, BORNDATE) VALUES"
				+ "(?,?,?,?,?,?,?,?,?)";

            preparedStatement = conn.prepareStatement(insertTableSQL);
            
            Integer idToDB = Integer.parseInt(id);
            Integer numberToDB = Integer.parseInt(number);
            Double heightToDB = Double.parseDouble(playerHeight);
            
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = format.parse(playerBornDate);
                System.out.println(date); // Sat Jan 02 00:00:00 GMT 2010
            } catch (ParseException ex) {
                Logger.getLogger(newPlayerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //Integer telToDB = Integer.parseInt(tel);
            InputStream is2 = new ByteArrayInputStream(baos.toByteArray()); 
            
            
            preparedStatement.setInt(1, idToDB);
            preparedStatement.setInt(2, numberToDB);
            preparedStatement.setString(3, playerName);
            preparedStatement.setBlob(4, is2);
            preparedStatement.setString(5, playerTeam);
            preparedStatement.setDouble(6, heightToDB);
            preparedStatement.setString(7, playerNationality);
            preparedStatement.setString(8, playerPosition);
            preparedStatement.setDate(9, new java.sql.Date(date.getTime()));

            // execute insert SQL stetement
            preparedStatement.executeUpdate();

            System.out.println("Record is inserted into PLAYER table!");
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

}
