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
public class GameStatus 
{
    private String homeTeam;
    private String awayTeam;
    private String status;
    private String id;

    /**
     * @return the homeTeam
     */
    public String getHomeTeam() {
        return homeTeam;
    }

    /**
     * @return the awayTeam
     */
    public String getAwayTeam() {
        return awayTeam;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param homeTeam the homeTeam to set
     */
    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    /**
     * @param awayTeam the awayTeam to set
     */
    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    
    
    
}
