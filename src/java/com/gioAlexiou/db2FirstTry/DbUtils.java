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

/**
 *
 * @author gioalexiou
 */
public class DbUtils 
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
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) 
        {
            Logger.getLogger(newTeamServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        // τέλος συνδεση στη ΒΔ.

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
    
    
    int teamScoreCalculation(String team, int id) throws SQLException
    {
        int finalScore = 0;
        
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
    
    ArrayList<Game> searchGamesForSpecificTeam(String teamName) throws SQLException
    {
        ArrayList<Game> returnedGames = new ArrayList();
        
        connectToDB();
        System.out.println(teamName);
        System.out.println("Inside searchGamesForSpecificTeam");
        try
        {
            //Find the team either it's home or away team
            String findTeamsMatch = "SELECT ID, HOMETEAM, AWAYTEAM FROM GAME WHERE HOMETEAM=\'" + teamName + "\' OR AWAYTEAM=\'" + teamName + "\'";
            preparedStatement = conn.prepareStatement(findTeamsMatch);
            resultSet = preparedStatement.executeQuery();
            
            System.out.println("After findTeamsMatch");
            while (resultSet.next()) 
            {
                //Retrieve the data from DB
                int gameIdRetrieved = Integer.parseInt(resultSet.getString(1));
                String homeTeamRetrieved = resultSet.getString(2);
                String awayTeamRetrieved = resultSet.getString(3);
                
                //Make the Game object
                Game gameRetrieved = new Game(gameIdRetrieved, homeTeamRetrieved, awayTeamRetrieved);
                returnedGames.add(gameRetrieved);
            }
            
        }
        catch (SQLException e) 
        {

            System.out.println(e.getMessage());
            return returnedGames;
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
        return returnedGames;
    }
    
    Game searchSpecificGameForTheTeam(int id) throws SQLException
    {
        Game gameRetrieved = null;
        connectToDB();
        
        try
        {
            //Find the team either it's home or away team
            String findGamefromGameId = "SELECT ID, HOMETEAM, AWAYTEAM FROM GAME WHERE ID=\'" + id +"\'";
            preparedStatement = conn.prepareStatement(findGamefromGameId);
            
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) 
            {
                //Retrieve the data from DB
                int gameIdRetrieved = Integer.parseInt(resultSet.getString(1));
                String homeTeamRetrieved = resultSet.getString(2);
                String awayTeamRetrieved = resultSet.getString(3);
                
                //Make the Game object
                gameRetrieved = new Game(gameIdRetrieved, homeTeamRetrieved, awayTeamRetrieved);
            }
            
        }
        catch (SQLException e) 
        {

            System.out.println(e.getMessage());
            return gameRetrieved;
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
        return gameRetrieved;
    }
    
    Player returnPlayerFromPlayerName(String playerName) 
    {
        connectToDB();
        Player retrievedPlayerName = new Player();
        try 
        {
            String selectAllPlayers = "SELECT ID, NUM, NAME FROM PLAYER WHERE NAME =\'" + playerName + "\'";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(selectAllPlayers);
            while (resultSet.next()) 
            {
                retrievedPlayerName.id = resultSet.getInt(1);
                retrievedPlayerName.number = resultSet.getInt(2);
                retrievedPlayerName.name = resultSet.getString(3);
            }
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
        return retrievedPlayerName;
    }
    
    ArrayList<Player> returnAllPlayers() 
    {
        connectToDB();
        ArrayList<Player> playerNames = new ArrayList<>();
        try 
        {
            String selectAllPlayers = "SELECT ID, NUM, NAME FROM PLAYER";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(selectAllPlayers);
            while (resultSet.next()) 
            {
                Player returnStatement = new Player();
                returnStatement.id = resultSet.getInt(1);
                returnStatement.number = resultSet.getInt(2);
                returnStatement.name = resultSet.getString(3);
                playerNames.add(returnStatement);
            }
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
        return playerNames;
    }
    
    ArrayList<PlayerStatsForGame> returnPlayerStatsFromGame(String playerId) 
    {
        connectToDB();
        ArrayList<PlayerStatsForGame> playerStats = new ArrayList<>();
        try 
        {
            String selectAllPlayers = "SELECT GAMEID, MININGAME, POINTS, TWOPOINTS, THREEPOINTS, FREETHROW, REBOUNDOFF, REBOUNDDEF, REBOUNDTOTAL, ASSIST, STEAL, MISTAKE, BLOCKFV, BLOCKAG, FOULCM, FOULRV, PIR FROM GAMEDATA WHERE PLAYERID =\'" + playerId + "\'";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(selectAllPlayers);
            while (resultSet.next()) 
            {
                PlayerStatsForGame returnStatement = new PlayerStatsForGame();
                returnStatement.setGameId(resultSet.getInt(1));
                returnStatement.setMinutesInGame(resultSet.getInt(2));
                returnStatement.setPoints(resultSet.getInt(3));
                returnStatement.setTwoPointsP(resultSet.getString(4));
                returnStatement.setThreePointsP(resultSet.getString(5));
                returnStatement.setFreeThrowP(resultSet.getString(6));
                returnStatement.setReboundsOff(resultSet.getInt(7));
                returnStatement.setReboundsDef(resultSet.getInt(8));
                returnStatement.setReboundsTot(resultSet.getInt(9));
                returnStatement.setAssists(resultSet.getInt(10));
                returnStatement.setSteals(resultSet.getInt(11));
                returnStatement.setMistakes(resultSet.getInt(12));
                returnStatement.setBlocksFv(resultSet.getInt(13));
                returnStatement.setBlocksAg(resultSet.getInt(14));
                returnStatement.setFoulCm(resultSet.getInt(15));
                returnStatement.setFoulRv(resultSet.getInt(16));
                returnStatement.setPir(resultSet.getInt(17));
                
                playerStats.add(returnStatement);
            }
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
        return playerStats;
    }
    
    String PlayerRankingIndex(String query)
    {
        connectToDB();
        String rankingPosition = "0";
        try 
        {
            String retrievePlayerRanking = "SELECT ID, NUM, NAME FROM PLAYER";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(retrievePlayerRanking);
            while (resultSet.next()) 
            {
                rankingPosition = resultSet.getString(1);
            }
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
        if (Integer.parseInt(rankingPosition) > 50 || Integer.parseInt(rankingPosition) == 0)
        {
            rankingPosition = "Out of top 50";
        }
        
        return rankingPosition;
    }
}
