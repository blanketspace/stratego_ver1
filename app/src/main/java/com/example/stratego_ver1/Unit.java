package com.example.stratego_ver1;

/**
 * class Unit
 *
 * @author Anne Marie Blank,
 * @author Harry Vu,
 * @author Vincent Truong
 * @version 2/24/2022
 */
public class Unit {
    /**
     *  External Citation
     *  Date 2/23/2022
     *  Issue: unsure what methods to implement
     *  "Link": Office Hours help from Nux
     *
     */
    public static final int SPY = 1;
    public static final int SCOUT = 2;
    public static final int MINER = 3;
    public static final int SERGEANT = 4;
    public static final int LIEUTENANT = 5;
    public static final int CAPTAIN = 6;
    public static final int MAJOR = 7;
    public static final int COLONEL = 8;
    public static final int GENERAL = 9;
    public static final int MARSHAL = 10;
    public static final int BOMB = 11;
    public static final int FLAG = 12;
    public static final int WATER = 13; //only completely non-movable piece


    /*** Nothing else needs to be added in this Unit class
     * IMPORTANT ELABORATION: Every unit has their ownerID, meaning that
     * the owner's id that comes with the unit,*/


    private int ownerID;  //the id of the player who owns the piece
    private int rank;     //what kind of unit is this?
    private boolean isSelected;
    private boolean isDead;
    private int xLoc;
    private int yLoc;

    public Unit(int id, int initRank){
        ownerID = id;
        rank = initRank;
        isSelected = false;
        isDead = false;
    }

    public int getOwnerID(){
        return this.ownerID;
    }

    public int getRank() {
        return rank;
    }

    public void setSelected(boolean selected) {
        if(!isDead) {
            isSelected = selected;
        }
    }
    public boolean getSelected(){
        return this.isSelected;
    }
    public void setStatus(boolean dead){
        this.isDead = dead;
    }

    public boolean getStatus(){
        return this.isDead;
    }

    public int getxLoc() {
        return this.xLoc;
    }

    public int getyLoc() {
        return this.yLoc;
    }

    public void setxLoc(int xLoc) {
        this.xLoc = xLoc;
    }

    public void setyLoc(int yLoc) {
        this.yLoc = yLoc;
    }
}
