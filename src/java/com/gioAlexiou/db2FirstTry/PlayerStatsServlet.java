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
        
        ArrayList<String> playersNameNum = new ArrayList();
        String newPlayer;
        for (int i=0; i<allPlayers.size(); i++)
        {
            newPlayer = allPlayers.get(i).name;
            playersNameNum.add(newPlayer);
        }
        int pointsSum = 0;
        int reboundsStopSum = 0;
        int mistakesSum = 0;
        int blocksFvSum = 0;
        for (int i=0; i< playerStats.size(); i++)
        {
            playerStats.get(i).removeFraction();
            pointsSum = pointsSum + playerStats.get(i).getPoints();
            reboundsStopSum = reboundsStopSum + playerStats.get(i).getReboundsTot();
            mistakesSum = mistakesSum + playerStats.get(i).getMistakes();
            blocksFvSum = blocksFvSum + playerStats.get(i).getBlocksFv();
        }
        
        //Step 2: Add Attributes to request object
        request.setAttribute("player_names_arraylist", playersNameNum);
        request.setAttribute("PlayerNameSelected", selectedPlayerName);
        request.setAttribute("gameStatusArraylist", playerStats);
        //Chart Attributes
        request.setAttribute("pointsSum", pointsSum);
        request.setAttribute("reboundsStopSum", reboundsStopSum);
        request.setAttribute("blocksFvSum", blocksFvSum);
        request.setAttribute("mistakesSum", mistakesSum);

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
