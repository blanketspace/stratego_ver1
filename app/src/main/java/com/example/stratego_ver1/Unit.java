package com.example.stratego_ver1;

import android.graphics.Canvas;

/**
 * class Unit
 *
 * @author Anne Marie Blank,
 * @author Harry Vu,
 * @author Vincent Truong
 * @version 3/11/2022
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

    //All the getter and setters for the different variables.
    public void setSelected(boolean selected) {
        //Check to see if the unit is dead or not.
        if(!isDead) {
            isSelected = selected;
        }
    }

    public int getOwnerID(){return this.ownerID;}

    public int getRank() { return rank;}

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

    /**
     * nameRank
     *
     * @return      The name of the unit.
     */
    public String nameRank() {
        String name;
        //Determined the rank based on the number rank of the unit
        switch (this.rank)
        {
            case 1:
                return "Spy";
            case 2:
                return "Scout";
            case 3:
                return "Miner";
            case 4:
                return "Sergeant";
            case 5:
                return "Lieutenant";
            case 6:
                return "Captain";
            case 7:
                return "Major";
            case 8:
                return "Colonel";
            case 9:
                return "General";
            case 10:
                return "Marshal";
            case 11:
                return "Bomb";
            case 12:
                return "Flag";
        }
        return "bad";
    }

}//class Unit
