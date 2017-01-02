/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gioAlexiou.db2FirstTry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FunUtils 
{
    Connection conn = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    String serverURL = "195.251.166.123";
    String serverPort = "1521";
    String serverSID = "orcl";
    String username = "c##icsd11138";
    String password = "icsd11138";
    ResultSet resultSet = null;
    
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
    
    public ArrayList<String> returnTeamNameFromTeam() 
    {
        connectToDB();
        ArrayList<String> teamNames = new ArrayList<>();
        try 
        {
            String selectTeamNameFromTeam = "SELECT NAME FROM TEAM";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(selectTeamNameFromTeam);
            while (resultSet.next()) 
            {
                String returnStatement = resultSet.getString(1);
                teamNames.add(returnStatement);
            }
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
        return teamNames;
    }
}
