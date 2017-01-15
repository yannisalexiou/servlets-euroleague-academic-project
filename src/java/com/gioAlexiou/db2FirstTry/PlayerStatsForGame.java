package com.gioAlexiou.db2FirstTry;

/**
 *
 * @author gioalexiou
 */
public class PlayerStatsForGame 
{
    private int playerId;
    private int gameId;
    private int minutesInGame;
    private int points;
    private int reboundsOff;
    private int reboundsDef;
    private int reboundsTot;
    private int assists;
    private int steals;
    private int mistakes;
    private int blocksFv;
    private int blocksAg;
    private int foulCm;
    private int foulRv;
    private int pir;
    
    private String twoPointsP;
    private int twoPointsSc;
    private int twoPointsTot;
    
    private String threePointsP;
    private int ThreePointsSc;
    private int threePointsTot;
    
    private String freeThrowP;
    private int freeThrowsSc;
    private int freeThrowsTot;
    
    
    
    public void removeFraction()
    {
        if (!twoPointsP.equals("0")) {
            String Splitted[] = getTwoPointsP().split("/");
            setTwoPointsSc(Integer.parseInt(Splitted[0]));
            setTwoPointsTot(Integer.parseInt(Splitted[1]));
        }
        if (!threePointsP.equals("0")) {
            String Splitted[] = getThreePointsP().split("/");
            setThreePointsSc(Integer.parseInt(Splitted[0]));
            setThreePointsTot(Integer.parseInt(Splitted[1]));
        }
        if (!freeThrowP.equals("0")) {
            String Splitted[] = getFreeThrowP().split("/");
            setFreeThrowsSc(Integer.parseInt(Splitted[0]));
            setFreeThrowsTot(Integer.parseInt(Splitted[1]));
        }
    }

    /**
     * @return the playerId
     */
    public int getPlayerId() {
        return playerId;
    }

    /**
     * @param playerId the playerId to set
     */
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    /**
     * @return the gameId
     */
    public int getGameId() {
        return gameId;
    }

    /**
     * @param gameId the gameId to set
     */
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    /**
     * @return the minutesInGame
     */
    public int getMinutesInGame() {
        return minutesInGame;
    }

    /**
     * @param minutesInGame the minutesInGame to set
     */
    public void setMinutesInGame(int minutesInGame) {
        this.minutesInGame = minutesInGame;
    }

    /**
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * @return the reboundsOff
     */
    public int getReboundsOff() {
        return reboundsOff;
    }

    /**
     * @param reboundsOff the reboundsOff to set
     */
    public void setReboundsOff(int reboundsOff) {
        this.reboundsOff = reboundsOff;
    }

    /**
     * @return the reboundsDef
     */
    public int getReboundsDef() {
        return reboundsDef;
    }

    /**
     * @param reboundsDef the reboundsDef to set
     */
    public void setReboundsDef(int reboundsDef) {
        this.reboundsDef = reboundsDef;
    }

    /**
     * @return the reboundsTot
     */
    public int getReboundsTot() {
        return reboundsTot;
    }

    /**
     * @param reboundsTot the reboundsTot to set
     */
    public void setReboundsTot(int reboundsTot) {
        this.reboundsTot = reboundsTot;
    }

    /**
     * @return the assists
     */
    public int getAssists() {
        return assists;
    }

    /**
     * @param assists the assists to set
     */
    public void setAssists(int assists) {
        this.assists = assists;
    }

    /**
     * @return the steals
     */
    public int getSteals() {
        return steals;
    }

    /**
     * @param steals the steals to set
     */
    public void setSteals(int steals) {
        this.steals = steals;
    }

    /**
     * @return the mistakes
     */
    public int getMistakes() {
        return mistakes;
    }

    /**
     * @param mistakes the mistakes to set
     */
    public void setMistakes(int mistakes) {
        this.mistakes = mistakes;
    }

    /**
     * @return the blocksFv
     */
    public int getBlocksFv() {
        return blocksFv;
    }

    /**
     * @param blocksFv the blocksFv to set
     */
    public void setBlocksFv(int blocksFv) {
        this.blocksFv = blocksFv;
    }

    /**
     * @return the blocksAg
     */
    public int getBlocksAg() {
        return blocksAg;
    }

    /**
     * @param blocksAg the blocksAg to set
     */
    public void setBlocksAg(int blocksAg) {
        this.blocksAg = blocksAg;
    }

    /**
     * @return the foulCm
     */
    public int getFoulCm() {
        return foulCm;
    }

    /**
     * @param foulCm the foulCm to set
     */
    public void setFoulCm(int foulCm) {
        this.foulCm = foulCm;
    }

    /**
     * @return the foulRv
     */
    public int getFoulRv() {
        return foulRv;
    }

    /**
     * @param foulRv the foulRv to set
     */
    public void setFoulRv(int foulRv) {
        this.foulRv = foulRv;
    }

    /**
     * @return the pir
     */
    public int getPir() {
        return pir;
    }

    /**
     * @param pir the pir to set
     */
    public void setPir(int pir) {
        this.pir = pir;
    }

    /**
     * @return the twoPointsP
     */
    public String getTwoPointsP() {
        return twoPointsP;
    }

    /**
     * @param twoPointsP the twoPointsP to set
     */
    public void setTwoPointsP(String twoPointsP) {
        this.twoPointsP = twoPointsP;
    }

    /**
     * @return the twoPointsSc
     */
    public int getTwoPointsSc() {
        return twoPointsSc;
    }

    /**
     * @param twoPointsSc the twoPointsSc to set
     */
    public void setTwoPointsSc(int twoPointsSc) {
        this.twoPointsSc = twoPointsSc;
    }

    /**
     * @return the twoPointsTot
     */
    public int getTwoPointsTot() {
        return twoPointsTot;
    }

    /**
     * @param twoPointsTot the twoPointsTot to set
     */
    public void setTwoPointsTot(int twoPointsTot) {
        this.twoPointsTot = twoPointsTot;
    }

    /**
     * @return the threePointsP
     */
    public String getThreePointsP() {
        return threePointsP;
    }

    /**
     * @param threePointsP the threePointsP to set
     */
    public void setThreePointsP(String threePointsP) {
        this.threePointsP = threePointsP;
    }

    /**
     * @return the ThreePointsSc
     */
    public int getThreePointsSc() {
        return ThreePointsSc;
    }

    /**
     * @param ThreePointsSc the ThreePointsSc to set
     */
    public void setThreePointsSc(int ThreePointsSc) {
        this.ThreePointsSc = ThreePointsSc;
    }

    /**
     * @return the threePointsTot
     */
    public int getThreePointsTot() {
        return threePointsTot;
    }

    /**
     * @param threePointsTot the threePointsTot to set
     */
    public void setThreePointsTot(int threePointsTot) {
        this.threePointsTot = threePointsTot;
    }

    /**
     * @return the freeThrowP
     */
    public String getFreeThrowP() {
        return freeThrowP;
    }

    /**
     * @param freeThrowP the freeThrowP to set
     */
    public void setFreeThrowP(String freeThrowP) {
        this.freeThrowP = freeThrowP;
    }

    /**
     * @return the freeThrowsSc
     */
    public int getFreeThrowsSc() {
        return freeThrowsSc;
    }

    /**
     * @param freeThrowsSc the freeThrowsSc to set
     */
    public void setFreeThrowsSc(int freeThrowsSc) {
        this.freeThrowsSc = freeThrowsSc;
    }

    /**
     * @return the freeThrowsTot
     */
    public int getFreeThrowsTot() {
        return freeThrowsTot;
    }

    /**
     * @param freeThrowsTot the freeThrowsTot to set
     */
    public void setFreeThrowsTot(int freeThrowsTot) {
        this.freeThrowsTot = freeThrowsTot;
    }
}
