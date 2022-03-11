package com.example.stratego_ver1;

import android.icu.text.UnicodeSetIterator;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * StrategoGameState
 *
 * (will eventually extend GameState interface in the GameFramework)
 *
 * @author Anne Marie Blank,
 * @author Harry Vu,
 * @author Vincent Truong,
 * @version 2/27/2022
 */
public class StrategoGameState {

    private int whoseTurn;

    private Unit[][] gameboard;
    private int roundNumber;     //will be initialized to zero, changed to indicate who's turn it is
    private double timeElapsed;  //for the timer
    private ArrayList<Unit> p1Troops;
    private ArrayList<Unit> p2Troops;

    private boolean flagCaptured;

    /**
     * ctor
     *
     * defines the state of the game on startup
     *
     */
    public StrategoGameState(){
        gameboard = new Unit[10][10];
        roundNumber = 0;
        whoseTurn = 0;
        timeElapsed = 0.0;
        flagCaptured = false;

        this.fillRanks(0);
        this.fillRanks(1);


    }//ctor

    /**
     * fillRanks
     *
     * helper method to fill the player's Troop Arrays
     */
    public void fillRanks(int pID) {
        if (pID == 0) {
            p1Troops.add(new Unit(0, Unit.MARSHAL));
            p1Troops.add(new Unit(0, Unit.GENERAL));
            p1Troops.add(new Unit(0, Unit.FLAG));
            p1Troops.add(new Unit(0, Unit.SPY));

            p1Troops.add(new Unit(0, Unit.COLONEL));
            p1Troops.add(new Unit(0, Unit.COLONEL));

            p1Troops.add(new Unit(0, Unit.MAJOR));
            p1Troops.add(new Unit(0, Unit.MAJOR));
            p1Troops.add(new Unit(0, Unit.MAJOR));

            for(int i = 0; i < 3; i++){
                p1Troops.add(new Unit(0, Unit.SERGEANT));
            }

            for(int i = 0; i < 3; i++){
                p1Troops.add(new Unit(0, Unit.LIEUTENANT));
            }

            for(int i = 0; i < 3; i++){
                p1Troops.add(new Unit(0, Unit.CAPTAIN));
            }

            for (int i = 0; i < 4; i++) {
                p1Troops.add(new Unit(0, Unit.MINER));
            }

            for (int i = 0; i < 7; i++) {
                p1Troops.add(new Unit(0, Unit.SCOUT));
            }

            for (int i = 0; i < 5; i++) {
                p1Troops.add(new Unit(0, Unit.BOMB));
            }

        } else if (pID == 1) {
            p2Troops.add(new Unit(0, Unit.MARSHAL));
            p2Troops.add(new Unit(0, Unit.GENERAL));
            p2Troops.add(new Unit(0, Unit.FLAG));
            p2Troops.add(new Unit(0, Unit.SPY));

            p2Troops.add(new Unit(0, Unit.COLONEL));
            p2Troops.add(new Unit(0, Unit.COLONEL));

            p2Troops.add(new Unit(0, Unit.MAJOR));
            p2Troops.add(new Unit(0, Unit.MAJOR));
            p2Troops.add(new Unit(0, Unit.MAJOR));

            p2Troops.add(new Unit(0, Unit.SERGEANT));
            p2Troops.add(new Unit(0, Unit.SERGEANT));
            p2Troops.add(new Unit(0, Unit.SERGEANT));
            p2Troops.add(new Unit(0, Unit.SERGEANT));

            p2Troops.add(new Unit(0, Unit.LIEUTENANT));
            p2Troops.add(new Unit(0, Unit.LIEUTENANT));
            p2Troops.add(new Unit(0, Unit.LIEUTENANT));
            p2Troops.add(new Unit(0, Unit.LIEUTENANT));

            p2Troops.add(new Unit(0, Unit.CAPTAIN));
            p2Troops.add(new Unit(0, Unit.CAPTAIN));
            p2Troops.add(new Unit(0, Unit.CAPTAIN));
            p2Troops.add(new Unit(0, Unit.CAPTAIN));

            for (int i = 0; i < 4; i++) {
                p2Troops.add(new Unit(0, Unit.MINER));
            }

            for (int i = 0; i < 7; i++) {
                p2Troops.add(new Unit(0, Unit.SCOUT));
            }

            for (int i = 0; i < 5; i++) {
                p2Troops.add(new Unit(0, Unit.BOMB));
            }
        }
    }//fillRanks

    /**
     * copy ctor
     *
     * @param orig  the original GameState to be copied
     * @Override
     */
    public StrategoGameState(StrategoGameState orig){
        //gameboard = orig.gameboard;

        for(int i = 0; i < gameboard.length; i++){
            for(int j = 0; j < gameboard[i].length; j++){
                gameboard[i][j] = orig.gameboard[i][j];
            }
        }
        flagCaptured = orig.flagCaptured;
        whoseTurn = orig.whoseTurn;
        roundNumber = orig.roundNumber;
        p1Troops = orig.p1Troops;
        p2Troops = orig.p2Troops;
    }//copy ctor

    /**
     * toString
     *
     * @return a String representation of the current StrategoGameState
     */
    @Override
    public String toString() {
        return "Turn:" + whoseTurn + "Player 1 Troops: " + p1Troops
                + "Player 2 Troops: " + p2Troops + "Time Elapsed: " + timeElapsed
                + "Flag Captured?: " + flagCaptured;
    }//toString

    /**
     * movePiece
     *
     *
     * External Citation
     * Date 2/23/2022
     * Issue: unsure what methods to implement
     *
     * "Link": Office Hours help from Nux
     *
     *
     * @param playerID id of the player moving the piece
     * @param dir      direction of intended movement (up/down/l/r)
     *
     * @return  false if move is illegal, true otherwise
     */
    public boolean movePiece(int playerID, Unit chosen, int dir){
        int chosenY = chosen.getyLoc();
        int chosenX = chosen.getxLoc();
        //1 = up, 2 = down, 3 = left, 4 = right
        if(dir == 1){
            if(gameboard[chosenX][chosenY- 1] == null && chosenY - 1 >= 0){
                chosen.setyLoc(chosenY - 1);
                gameboard[chosenX][chosenY] = chosen;
                return true;
            }
            else if(gameboard[chosenX][chosenY - 1].getRank() == Unit.WATER){
                return false;
            }
            else if (gameboard[chosenX][chosenY].getOwnerID() != playerID) {
                //attack
                int opponentRank = gameboard[chosenX][chosenY].getRank();
                if (opponentRank > chosen.getRank()) {
                    chosen.setStatus(false);
                    gameboard[chosenX][chosenY] = null;
                    return true;
                } else {
                    gameboard[chosenX][chosenY].setStatus(false);
                    gameboard[chosenX][chosenY] = null;
                    chosen.setxLoc(chosenY - 1);
                    gameboard[chosenX][chosenY] = chosen;
                    return true;
                }
            }
                else{
                    return false;
                }

        }
        else if(dir == 2){
            if(gameboard[chosenX][chosenY + 1] == null && chosenY + 1 <= 9){  //aka space is empty
                chosen.setyLoc(chosenY + 1);  //move into space
                gameboard[chosenX][chosenY] = chosen;
                return true;
            }
            else if(gameboard[chosenX][chosenY + 1].getRank() == Unit.WATER){
                return false;  //can't walk on water
            }
            else if(gameboard[chosenX][chosenY + 1].getOwnerID() != playerID){
                    //attack
                int opponentRank = gameboard[chosenX][chosenY + 1].getRank();
                if(opponentRank > chosen.getRank()){
                    chosen.setStatus(false);  //you died
                    gameboard[chosenX][chosenY] = null;  //empty space you were just in
                    return true;
                }
                else {
                    gameboard[chosenX][chosenY + 1].setStatus(false);  //they died
                    gameboard[chosenX][chosenY] = null;  //empty the space you were just in
                    chosen.setyLoc(chosenY + 1);  //move into opponent's space
                    gameboard[chosenX][chosenY + 1] = chosen;  //report location to array
                    return true;
                }
            }
            else {
                return false;
            }
        }
        else if(dir == 3) {
            if (gameboard[chosenX - 1][chosenY] == null && chosenX - 1 >= 0) {
                chosen.setxLoc(chosenX - 1);
                gameboard[chosenX - 1][chosenY] = chosen;
                return true;
            }
            else if (gameboard[chosenX - 1][chosenY].getRank() == Unit.WATER) {
                return false;
            }
            else if (gameboard[chosenX - 1][chosenY].getOwnerID() != playerID) {
                //attack
                int opponentRank = gameboard[chosenX - 1][chosenY].getRank();

                if (opponentRank > chosen.getRank()) {
                    chosen.setStatus(false);  //you died
                    gameboard[chosenX][chosenY] = null;
                    return true;
                }
                else {
                    gameboard[chosenX - 1][chosenY].setStatus(false);  //they died
                    gameboard[chosenX][chosenY] = null;  //empty your spot
                    chosen.setxLoc(chosenX - 1);
                    gameboard[chosenX - 1][chosenY] = chosen;  //take their spot
                    return true;
                }
            }
            else {
                return false;
            }
        }
        else if(dir == 4){
            if(chosenX + 1 <= 9){
                if(gameboard[chosenX + 1][chosenY] == null && chosenX + 1<= 9) {
                    chosen.setxLoc(chosenX + 1);
                    gameboard[chosenX + 1][chosenY] = chosen;
                    return true;
                }
                else if(gameboard[chosenX + 1][chosenY].getRank() == Unit.WATER){
                    return false;
                }
                else {
                    if(gameboard[chosenX + 1][chosenY].getOwnerID() != playerID){
                        //attack
                        int opponentRank = gameboard[chosenX + 1][chosenY].getRank();

                        if(opponentRank > chosen.getRank()){
                            chosen.setStatus(false);  //you died
                            gameboard[chosenX][chosenY] = null;
                            return true;
                        }
                        else if(opponentRank <= chosen.getRank()){
                            gameboard[chosenX + 1][chosenY].setStatus(false);  //they died
                            gameboard[chosenX][chosenY] = null;  //empty your space
                            chosen.setxLoc(chosenX + 1);
                            gameboard[chosenX + 1][chosenY] = chosen;  //take theirs
                            return true;
                        }
                        else{
                            return false;
                        }
                    }
                    else{
                        return false;
                    }
                }
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }//movePiece


    /**
     * selectPiece
     *
     *
     * @param playerID   the id of the player attempting to choose
     * @param chosenP    the Unit being selected
     */
    public boolean selectPiece(int playerID, Unit chosenP){
        if(chosenP.getOwnerID() == playerID){
            clearSelection(playerID);  //sets all Units to false
            chosenP.setSelected(true); //sets selection to true
            return true;
        }
        else {
            return false;
        }
    }//selectPiece

    /**
     * clearSelection
     *
     * sets the isSelected value of all Units in the player's "hand" to false
     *
     * @param playerId  the ID of the user attempting to make a selection
     */
    public void clearSelection(int playerId){
        if(playerId == 0){
            for(int i= 0; i <= p1Troops.size(); i++){
                p1Troops.get(i).setSelected(false);
            }
        }
        else if(playerId == 1){
            for(int i= 0; i <= p2Troops.size(); i++){
                p2Troops.get(i).setSelected(false);
            }
        }
    }//clearSelection

    /**
     * placePiece
     *
     * TODO: isn't this action already handled in movePiece?
     *
     * @param playerID
     * @param unit
     * @param x
     * @param y
     * @return
     */
    public boolean placePiece(int playerID, Unit unit, int x, int y) {
        if (unit.getStatus()) {
            if (playerID == 0 && y < 4) {
                unit.setxLoc(x);
                unit.setxLoc(y);
                gameboard[x][y] = unit;
                return true;
            }
            else if (playerID == 1 && y > 5) {
                unit.setxLoc(x);
                unit.setxLoc(y);
                gameboard[x][y] = unit;
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }//placePiece

}//StrategoGameState

