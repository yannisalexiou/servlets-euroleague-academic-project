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
@WebServlet("/BestFiveInRoundPlayersResults")
@MultipartConfig
public class BestFiveInRoundPlayersServlet extends HttpServlet {

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
        //Step 1: Retrieve Parameters from Form
        String selectedRoundNumber = request.getParameter("roundNumberSelected");
        String indexRatingColumnName = "PIR";
        String pointsColumnName = "POINTS";
        String reboundsColumnName = "REBOUNDTOTAL";
        String blocksFvColumnName = "BLOCKFV";
        String assistsColumnName = "ASSIST";
        
        //Step 2: Connect to Database and Retrieve the Values
        DbUtils dbConnection = new DbUtils();
        ArrayList<String> allRounds = dbConnection.returnRoundsFromGame();
        
        ArrayList<RetrievedBestFiveData> bestFivePirRetrieved = dbConnection.returnBestFive(selectedRoundNumber, indexRatingColumnName);
        ArrayList<RetrievedBestFiveData> bestFivePointsRetrieved = dbConnection.returnBestFive(selectedRoundNumber, pointsColumnName);
        ArrayList<RetrievedBestFiveData> bestFiveReboundsRetrieved = dbConnection.returnBestFive(selectedRoundNumber, reboundsColumnName);
        ArrayList<RetrievedBestFiveData> bestFiveBlocksRetrieved = dbConnection.returnBestFive(selectedRoundNumber, blocksFvColumnName);
        ArrayList<RetrievedBestFiveData> bestFiveAssistsRetrieved = dbConnection.returnBestFive(selectedRoundNumber, assistsColumnName);
        
        ArrayList<BestFiveInRound> bestFivePirToDisplay = bestFiveToDisplay(bestFivePirRetrieved);
        ArrayList<BestFiveInRound> bestFivePointsToDisplay = bestFiveToDisplay(bestFivePointsRetrieved);
        ArrayList<BestFiveInRound> bestFiveReboundsToDisplay = bestFiveToDisplay(bestFiveReboundsRetrieved);
        ArrayList<BestFiveInRound> bestFiveBlocksToDisplay = bestFiveToDisplay(bestFiveBlocksRetrieved);
        ArrayList<BestFiveInRound> bestFiveAssistsToDisplay = bestFiveToDisplay(bestFiveAssistsRetrieved);
        
        //Step 3: Add Students to request object
        request.setAttribute("round_number_arraylist", allRounds);
        request.setAttribute("best5InIndexRatingArraylist", bestFivePirToDisplay);
        request.setAttribute("best5InPointsArraylist", bestFivePointsToDisplay);
        request.setAttribute("best5InReboundsArrayList", bestFiveReboundsToDisplay);
        request.setAttribute("best5InBlocksArrayList", bestFiveBlocksToDisplay);
        request.setAttribute("best5InAssitsArrayList", bestFiveAssistsToDisplay);
        
        //Step 4: Get Request dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view_best_five_of_round_results.jsp");

        //Step 5: Forward the request to JSP
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
            throws ServletException, IOException {
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
    
    ArrayList<BestFiveInRound> bestFiveToDisplay(ArrayList<RetrievedBestFiveData> retrievedBestFiveData)
    {
        DbUtils dbConnection = new DbUtils();
        ArrayList<Player> retrievedPlayerInfo = new ArrayList<>();
        for (int i=0; i<retrievedBestFiveData.size(); i++)
        {
            Player retrievedPlayer = dbConnection.returnPlayerFromPlayerId(retrievedBestFiveData.get(i).playerId);
            retrievedPlayerInfo.add(retrievedPlayer);
        }
        
        ArrayList<BestFiveInRound> bestFiveToDisplay =  new ArrayList<>();
        BestFiveInRound bestFiveItem = new BestFiveInRound();
        for (int i=0; i<retrievedBestFiveData.size(); i++)
        {
            bestFiveItem.setRowNumber(retrievedBestFiveData.get(i).rowNumber);
            bestFiveItem.setStatistic(retrievedBestFiveData.get(i).statistic);
            bestFiveItem.setPlayerName(retrievedPlayerInfo.get(i).name);
            bestFiveItem.setPlayerTeam(retrievedPlayerInfo.get(i).team);
            bestFiveItem.setPlayerNationality(retrievedPlayerInfo.get(i).nationality);
            
            bestFiveToDisplay.add(bestFiveItem);
        }
        
        return bestFiveToDisplay;
    }

}
