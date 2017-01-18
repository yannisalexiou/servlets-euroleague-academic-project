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
import java.util.Arrays;
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


@WebServlet("/upload")
@MultipartConfig
public class newTeamServlet extends HttpServlet 
{
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
    String teamName;
    String ceo;
    String coach;
    String arena;
    String address;
    String tel;
    InputStream fileContent;
      
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
            throws ServletException, IOException 
    {
        
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        //Step 1: Create path components to save the file
        fileContent = null;
        
        id = request.getParameter("id");
        teamName = request.getParameter("teamName"); // Retrieves <input type="text" name="teamName">
        ceo = request.getParameter("ceo");
        coach = request.getParameter("coach");
        arena = request.getParameter("arena");
        address = request.getParameter("address");
        tel = request.getParameter("tel");
        
        //Step 2: Retrieving the data from post request
        Part filePart = request.getPart("teamLogo"); // Retrieves <input type="file" name="file">
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
        
        //Connect to Server
        ConnectToDB();
        try {
            insertTeamToDb(baos);
        } catch (SQLException ex) {
            Logger.getLogger(newTeamServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Step 5: Add retrieved data to attributes
        request.setAttribute("id", id);
        request.setAttribute("teamName", teamName);
        request.setAttribute("teamLogo", fileName);
        request.setAttribute("ceo", ceo);
        request.setAttribute("coach", coach);
        request.setAttribute("arena", arena);
        request.setAttribute("address", address);
        request.setAttribute("tel", tel);
        
        //Step 6: Get Request dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view_new_team.jsp");
        
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
    
    void insertTeamToDb(ByteArrayOutputStream baos) throws SQLException
    {
        //run insert από τα δεδομένα της φόρμας
        
        try
        {
            
            String insertTableSQL = "INSERT INTO TEAM"
				+ "(NAME, LOGO, CEO, COACH, ARENA, ADDRESS, TEL, ID) VALUES"
				+ "(?,?,?,?,?,?,?,?)";

            preparedStatement = conn.prepareStatement(insertTableSQL);
            
            Integer idToDB = Integer.parseInt(id);
            //Integer telToDB = Integer.parseInt(tel);
            InputStream is2 = new ByteArrayInputStream(baos.toByteArray()); 
            
            preparedStatement.setString(1, teamName);
            preparedStatement.setBinaryStream(2, is2);
            preparedStatement.setString(3, ceo);
            preparedStatement.setString(4, coach);
            preparedStatement.setString(5, arena);
            preparedStatement.setString(6, address);
            preparedStatement.setString(7, tel);
            preparedStatement.setInt(8, idToDB);

            // execute insert SQL stetement
            preparedStatement.executeUpdate();

            System.out.println("Record is inserted into TEAM table!");
                        
            //String qString = "INSERT INTO TEAM VALUES("+teamName+",'"+ Arrays.toString(getImage()) +"','"+ceo+ "','" + coach + "'," + arena + "','" + address + "','" + tel + "','" + id +")";

            //statement = conn.createStatement();
            //statement.execute(qString);
            //statement.close();
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
