/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gioAlexiou.db2FirstTry;

/**
 *
 * @author gioalexiou
 */
public class Game 
{
    int gameId;
    String homeTeam;
    String awayTeam;
    
    Game (int gameId, String homeTeam, String awayTeam)
    {
        this.gameId = gameId;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }
}
