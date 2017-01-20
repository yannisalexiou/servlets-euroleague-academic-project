/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gioAlexiou.db2FirstTry;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gioalexiou
 */
@WebServlet("/TeamStatsResults")
@MultipartConfig
public class TeamStatsServlet extends HttpServlet {

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
    ResultSet resultSet = null;
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException 
    {
        String selectedTeamName = request.getParameter("teamNameSelected");
        DbUtils dbConnection = new DbUtils();
        
        ArrayList<String> teamNames =  dbConnection.returnTeamNameFromTeam();

        //Step 2: Add Students to request object
        request.setAttribute("team_names_arraylist", teamNames);

        //Find Team's Games
        ArrayList<Game> teamGames = dbConnection.searchGamesForSpecificTeam(selectedTeamName);
        ArrayList<GameStatus> gameStatuses= new ArrayList();

        //Calculate Team's wins and loees
        Game thisGame = null;
        GameStatus thisGameStatus;
        int wins = 0;
        int loses = 0;
        for (int i=0; i<teamGames.size(); i++)
        {
            thisGame = dbConnection.searchSpecificGameForTheTeam(teamGames.get(i).gameId);
            //Find the Score for this Game
            int homeScore = dbConnection.teamScoreCalculation(teamGames.get(i).homeTeam, teamGames.get(i).gameId);
            int awayScore = dbConnection.teamScoreCalculation(teamGames.get(i).awayTeam, teamGames.get(i).gameId);

            thisGameStatus = new GameStatus();
            thisGameStatus.setHomeTeam(teamGames.get(i).homeTeam);
            thisGameStatus.setAwayTeam(teamGames.get(i).awayTeam);
            thisGameStatus.setId(Integer.toString(i+1));

            if (homeScore > awayScore)
            {

                thisGameStatus.setStatus("Winner - Loser");
                if (teamGames.get(i).homeTeam.equals(selectedTeamName))
                {
                    wins = wins + 1;
                }
                else
                {
                    loses = loses + 1;
                }
            }
            else
            {
                thisGameStatus.setStatus("Loser - Winner");
                if(teamGames.get(i).awayTeam.equals(selectedTeamName))
                {
                    wins = wins + 1;
                }
                else
                {
                    loses = loses + 1;
                }
            }

            gameStatuses.add(thisGameStatus);
        }


        //Step 1: Get the Team Names from Helper class (Model)
        //ArrayList<String> teamNames =  returnTeamNameFromTeam();

        //Step 2: Add Attributes to request object
        request.setAttribute("team_names_arraylist", teamNames);
        request.setAttribute("teamNameSelected", selectedTeamName);
        request.setAttribute("gameStatusArraylist", gameStatuses);
        request.setAttribute("wins", wins);
        request.setAttribute("loses", loses);

        //Step 3: Get Request dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view_team_results.jsp");

        //Step 4: Forward the request to JSP
        dispatcher.forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(TeamStatsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(TeamStatsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    

}
