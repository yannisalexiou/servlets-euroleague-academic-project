/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gioAlexiou.db2FirstTry;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet("/PlayerStatsResults")
@MultipartConfig
public class PlayerStatsServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String selectedPlayerName = request.getParameter("PlayerNameSelected");
        
        DbUtils dbConnection = new DbUtils();
        ArrayList<Player> allPlayers = dbConnection.returnAllPlayers();
        
        Player retrievedPlayer = dbConnection.returnPlayerFromPlayerName(selectedPlayerName);
        ArrayList<PlayerStatsForGame> playerStats = dbConnection.returnPlayerStatsFromGame(Integer.toString(retrievedPlayer.id));
        
        //Player Ranking Queries String
        String playerPirRankQuery = "SELECT Rank FROM( SELECT row_number() OVER (ORDER BY PIR DESC) as Rank, PIR, PLAYERID FROM ( SELECT SUM(PIR) as PIR, PLAYERID FROM GAMEDATA GROUP BY PLAYERID ORDER BY PIR DESC)) WHERE PLAYERID='" + retrievedPlayer.id + "'";
        String playerPointsRankQuery = "SELECT Rank FROM( SELECT row_number() OVER (ORDER BY POINTS DESC) as Rank, POINTS, PLAYERID  FROM ( SELECT SUM(POINTS) as POINTS, PLAYERID FROM GAMEDATA GROUP BY PLAYERID ORDER BY POINTS DESC) ) WHERE PLAYERID='" + retrievedPlayer.id + "'";
        String playerReboundsRankQuery = "SELECT Rank FROM( SELECT row_number() OVER (ORDER BY REBOUNDTOTAL DESC) as Rank, REBOUNDTOTAL, PLAYERID  FROM ( SELECT SUM(REBOUNDTOTAL) as REBOUNDTOTAL, PLAYERID FROM GAMEDATA GROUP BY PLAYERID ORDER BY REBOUNDTOTAL DESC) ) WHERE PLAYERID='" + retrievedPlayer.id + "'";
        String playerMistakesRankQuery = "SELECT Rank FROM( SELECT row_number() OVER (ORDER BY MISTAKE DESC) as Rank, MISTAKE, PLAYERID  FROM ( SELECT SUM(MISTAKE) as MISTAKE, PLAYERID FROM GAMEDATA GROUP BY PLAYERID ORDER BY MISTAKE DESC)) WHERE PLAYERID='" + retrievedPlayer.id + "'";
        
        String playerPirRankingPosition = dbConnection.playerRankingIndex(playerPirRankQuery);
        String playerPointsRankingPosition = dbConnection.playerRankingIndex(playerPointsRankQuery);
        String playerReboundsRankingPosition = dbConnection.playerRankingIndex(playerReboundsRankQuery);
        String playerMistakesRankingPosition = dbConnection.playerRankingIndex(playerMistakesRankQuery);
        
        ArrayList<String> playersNameNum = new ArrayList();
        String newPlayer;
        for (int i=0; i<allPlayers.size(); i++)
        {
            newPlayer = allPlayers.get(i).name;
            playersNameNum.add(newPlayer);
        }
        
         //Retrieve the data for bar chart, (sum calculation)
        int pointsSum = 0;
        int reboundsStopSum = 0;
        int mistakesSum = 0;
        int blocksFvSum = 0;
        
        //
        int freeThrowScoreSum = 0; //Also for gauge
        int freeThrowTotalSum = 0; //Also for gauge
        int twoPointsScoreSun = 0; //Also for gauge
        int twoPointsTotalSum = 0; //Also for gauge
        int threePointsScoreSum = 0; //Also for gauge
        int threePointsTotalSum = 0; //Also for gauge
        
        for (int i=0; i< playerStats.size(); i++)
        {
            //Bar Chart
            playerStats.get(i).removeFraction();
            pointsSum = pointsSum + playerStats.get(i).getPoints();
            reboundsStopSum = reboundsStopSum + playerStats.get(i).getReboundsTot();
            mistakesSum = mistakesSum + playerStats.get(i).getMistakes();
            blocksFvSum = blocksFvSum + playerStats.get(i).getBlocksFv();
            
            //Gauge Chart
            freeThrowScoreSum = freeThrowScoreSum + playerStats.get(i).getFreeThrowsSc();
            freeThrowTotalSum = freeThrowTotalSum + playerStats.get(i).getFreeThrowsTot();
            twoPointsScoreSun = twoPointsScoreSun + playerStats.get(i).getTwoPointsSc();
            twoPointsTotalSum = twoPointsTotalSum + playerStats.get(i).getTwoPointsTot();
            threePointsScoreSum = threePointsScoreSum + playerStats.get(i).getThreePointsSc();
            threePointsTotalSum = threePointsTotalSum + playerStats.get(i).getThreePointsTot();
        }
        
        //Step 2: Add Attributes to request object
        request.setAttribute("player_names_arraylist", playersNameNum);
        request.setAttribute("PlayerNameSelected", selectedPlayerName);
        request.setAttribute("gameStatusArraylist", playerStats);
        
        //Bar Chart Attributes
        request.setAttribute("pointsSum", pointsSum);
        request.setAttribute("reboundsStopSum", reboundsStopSum);
        request.setAttribute("blocksFvSum", blocksFvSum);
        request.setAttribute("mistakesSum", mistakesSum);
        
        //Gauge Chart Attributes
        request.setAttribute("freeThrowScoreSum", freeThrowScoreSum);
        request.setAttribute("freeThrowTotalSum", freeThrowTotalSum);
        request.setAttribute("twoPointsScoreSun", twoPointsScoreSun);
        request.setAttribute("twoPointsTotalSum", twoPointsTotalSum);
        request.setAttribute("threePointsScoreSum", threePointsScoreSum);
        request.setAttribute("threePointsTotalSum", threePointsTotalSum);
        
        //Ranking Attributes
        request.setAttribute("playerPirRankingPosition", playerPirRankingPosition);
        request.setAttribute("playerPointsRankingPosition", playerPointsRankingPosition);
        request.setAttribute("playerReboundsRankingPosition", playerReboundsRankingPosition);
        request.setAttribute("playerMistakesRankingPosition", playerMistakesRankingPosition);

        //Step 3: Get Request dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view_player_stats_results.jsp");

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
        processRequest(request, response);
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
