package com.example.stratego_ver1;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * StrategoGameState
 *
 * (will eventually extend GameState interface in the GameFramework)
 *
 * @authors Anne Marie Blank, Harry Vu, Vincent Truong, Kathryn Weidman
 * @version 2/24/2022
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
     * 1-- 10,9
     * 2-- 8
     * 3-- 7
     * 4-- 5, 4
     * 5-- 3
     * 8-- 2
     * 1-- spy(1)
     * 6-- bomb(12)
     * 1-- flag(13)
     *
     * fillRanks
     *
     * helper method to fill the player's Troop Arrays
     */
    public void fillRanks(int pID) {
        if (pID == 0) {
            p1Troops.add(new Unit(0, 10));
            p1Troops.add(new Unit(0, 9));
            p1Troops.add(new Unit(0, 13));
            p1Troops.add(new Unit(0, 1));
            p1Troops.add(new Unit(0, 8));
            p1Troops.add(new Unit(0, 8));

            p1Troops.add(new Unit(0, 7));
            p1Troops.add(new Unit(0, 7));
            p1Troops.add(new Unit(0, 7));

            p1Troops.add(new Unit(0, 4));
            p1Troops.add(new Unit(0, 4));
            p1Troops.add(new Unit(0, 4));
            p1Troops.add(new Unit(0, 4));

            p1Troops.add(new Unit(0, 5));
            p1Troops.add(new Unit(0, 5));
            p1Troops.add(new Unit(0, 5));
            p1Troops.add(new Unit(0, 5));

            p1Troops.add(new Unit(0, 6));
            p1Troops.add(new Unit(0, 6));
            p1Troops.add(new Unit(0, 6));
            p1Troops.add(new Unit(0, 6));

            for (int i = 0; i < 4; i++) {
                p1Troops.add(new Unit(0, 3));
            }

            for (int i = 0; i < 7; i++) {
                p1Troops.add(new Unit(0, 2));
            }

            for (int i = 0; i < 5; i++) {
                p1Troops.add(new Unit(0, 12));
            }

        } else if (pID == 1) {
            p2Troops.add(new Unit(0, 10));
            p2Troops.add(new Unit(0, 9));
            p2Troops.add(new Unit(0, 13));
            p2Troops.add(new Unit(0, 1));

            p2Troops.add(new Unit(0, 8));
            p2Troops.add(new Unit(0, 8));

            p2Troops.add(new Unit(0, 7));
            p2Troops.add(new Unit(0, 7));
            p2Troops.add(new Unit(0, 7));

            p2Troops.add(new Unit(0, 4));
            p2Troops.add(new Unit(0, 4));
            p2Troops.add(new Unit(0, 4));
            p2Troops.add(new Unit(0, 4));

            p2Troops.add(new Unit(0, 5));
            p2Troops.add(new Unit(0, 5));
            p2Troops.add(new Unit(0, 5));
            p2Troops.add(new Unit(0, 5));

            p2Troops.add(new Unit(0, 6));
            p2Troops.add(new Unit(0, 6));
            p2Troops.add(new Unit(0, 6));
            p2Troops.add(new Unit(0, 6));

            for (int i = 0; i < 4; i++) {
                p2Troops.add(new Unit(0, 3));
            }

            for (int i = 0; i < 7; i++) {
                p2Troops.add(new Unit(0, 2));
            }

            for (int i = 0; i < 5; i++) {
                p2Troops.add(new Unit(0, 12));
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
        gameboard = orig.gameboard;
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
        //1 = up, 2 = down, 3 = left, 4 = right
        if(dir == 1){
            if(gameboard[chosen.getxLoc()][chosen.getyLoc() - 1] == null && chosen.getyLoc() - 1 >= 0){
                chosen.setyLoc(chosen.getyLoc() - 1);
                gameboard[chosen.getxLoc()][chosen.getyLoc()] = chosen;
                return true;
            }
            else if(gameboard[chosen.getxLoc()][chosen.getyLoc() - 1].getRank() == Unit.WATER){
                return false;
            }

            //if the opponent's piece is a bomb tile
            else if(gameboard[chosen.getxLoc()][chosen.getyLoc() - 1].getRank() == Unit.BOMB){
                return isMinerAttack(chosen.getRank());
            }
            else if(gameboard[chosen.getxLoc()][chosen.getyLoc() - 1].getRank() == Unit.FLAG){
                flagCaptured = true;
                return true;
            }

            else if (gameboard[chosen.getxLoc()][chosen.getyLoc()].getOwnerID() != playerID) {
                //attack
                int opponentRank = gameboard[chosen.getxLoc()][chosen.getyLoc()].getRank();
                if (opponentRank > chosen.getRank()) {
                    chosen.setStatus(false);
                    gameboard[chosen.getxLoc()][chosen.getyLoc()] = null;
                    return true;
                } else if (opponentRank < chosen.getRank()) {
                    gameboard[chosen.getxLoc()][chosen.getyLoc()].setStatus(false);
                    gameboard[chosen.getxLoc()][chosen.getyLoc()] = null;
                    chosen.setxLoc(chosen.getyLoc() - 1);
                    gameboard[chosen.getxLoc()][chosen.getyLoc()] = chosen;
                    return true;
                } else { //equal rank, attacker wins
                    gameboard[chosen.getxLoc()][chosen.getyLoc()].setStatus(false);
                    gameboard[chosen.getxLoc()][chosen.getyLoc()] = null;
                    chosen.setxLoc(chosen.getyLoc() - 1);
                    gameboard[chosen.getxLoc()][chosen.getyLoc()] = chosen;
                    return true;
                }
            }
                else{
                    return false;
                }

        }
        else if(dir == 2){
            if(gameboard[chosen.getxLoc()][chosen.getyLoc() + 1] == null && chosen.getyLoc() + 1 <= 9){
                chosen.setyLoc(chosen.getyLoc() + 1);
                gameboard[chosen.getxLoc()][chosen.getyLoc()] = chosen;
                return true;
            }
            else if(gameboard[chosen.getxLoc()][chosen.getyLoc() + 1].getRank() == Unit.WATER){
                return false;
            }

            //if the opponent's piece is a bomb tile
            else if(gameboard[chosen.getxLoc()][chosen.getyLoc() + 1].getRank() == Unit.BOMB){
                return isMinerAttack(chosen.getRank());
            }
            else if(gameboard[chosen.getxLoc()][chosen.getyLoc() + 1].getRank() == Unit.FLAG){
                flagCaptured = true;
                return true;
            }

            else if(gameboard[chosen.getxLoc()][chosen.getyLoc()].getOwnerID() != playerID){
                    //attack
                    int opponentRank = gameboard[chosen.getxLoc()][chosen.getyLoc()].getRank();
                    if(opponentRank > chosen.getRank()){
                        chosen.setStatus(false);
                        gameboard[chosen.getxLoc()][chosen.getyLoc()] = null;
                        return true;
                    }
                    else if(opponentRank < chosen.getRank()){
                        gameboard[chosen.getxLoc()][chosen.getyLoc()].setStatus(false);
                        gameboard[chosen.getxLoc()][chosen.getyLoc()] = null;
                        chosen.setxLoc(chosen.getyLoc() + 1);
                        gameboard[chosen.getxLoc()][chosen.getyLoc()] = chosen;
                        return true;
                    }
                    else{ //equal rank, attacker wins
                        gameboard[chosen.getxLoc()][chosen.getyLoc()].setStatus(false);
                        gameboard[chosen.getxLoc()][chosen.getyLoc()] = null;
                        chosen.setxLoc(chosen.getyLoc() + 1);
                        gameboard[chosen.getxLoc()][chosen.getyLoc()] = chosen;
                        return true;
                    }

            }
            else {
                return false;
            }
        }
        else if(dir == 3){
            if(gameboard[chosen.getxLoc() - 1][chosen.getyLoc()] == null && chosen.getxLoc() - 1 >= 0){
                chosen.setxLoc(chosen.getxLoc() - 1);
                gameboard[chosen.getxLoc()][chosen.getyLoc()] = chosen;
                return true;
            }
            else if(gameboard[chosen.getxLoc() - 1][chosen.getyLoc()].getRank() == Unit.WATER){
                return false;
            }

            //if the opponent's piece is a bomb tile
            else if(gameboard[chosen.getxLoc() - 1][chosen.getyLoc()].getRank() == Unit.BOMB){
                return isMinerAttack(chosen.getRank());
            }
            else if(gameboard[chosen.getxLoc() - 1][chosen.getyLoc()].getRank() == Unit.FLAG){
                flagCaptured = true;
                return true;
            }

            else if(gameboard[chosen.getxLoc()][chosen.getyLoc()].getOwnerID() != playerID){
                    //attack
                    int opponentRank = gameboard[chosen.getxLoc()][chosen.getyLoc()].getRank();
                    if(opponentRank > chosen.getRank()){
                        chosen.setStatus(false);
                        gameboard[chosen.getxLoc()][chosen.getyLoc()] = null;
                        return true;
                    }
                    else if(opponentRank < chosen.getRank()){
                        gameboard[chosen.getxLoc()][chosen.getyLoc()].setStatus(false);
                        gameboard[chosen.getxLoc()][chosen.getyLoc()] = null;
                        chosen.setxLoc(chosen.getxLoc() - 1);
                        gameboard[chosen.getxLoc()][chosen.getyLoc()] = chosen;
                        return true;
                    }
                    else{ //equal rank, attacker wins
                        gameboard[chosen.getxLoc()][chosen.getyLoc()].setStatus(false);
                        gameboard[chosen.getxLoc()][chosen.getyLoc()] = null;
                        chosen.setxLoc(chosen.getxLoc() - 1);
                        gameboard[chosen.getxLoc()][chosen.getyLoc()] = chosen;
                        return true;
                    }

                }
            else {
                return false;
            }

        }
        else if(dir == 4){
            if(chosen.getxLoc() + 1 <= 9){
                if(gameboard[chosen.getxLoc() + 1][chosen.getyLoc()] == null) {
                    chosen.setxLoc(chosen.getxLoc() + 1);
                    gameboard[chosen.getxLoc()][chosen.getyLoc()] = chosen;
                    return true;
                }
                else if(gameboard[chosen.getxLoc() + 1][chosen.getyLoc()].getRank() == Unit.WATER){
                    return false;
                }

                //if the opponent's piece is a bomb tile
                else if(gameboard[chosen.getxLoc() + 1][chosen.getyLoc()].getRank() == Unit.BOMB){
                    return isMinerAttack(chosen.getRank());
                }
                else if(gameboard[chosen.getxLoc() + 1][chosen.getyLoc()].getRank() == Unit.FLAG){
                    flagCaptured = true;
                    return true;
                }
                else {
                    if(gameboard[chosen.getxLoc()][chosen.getyLoc()].getOwnerID() != playerID){
                        //attack
                        int opponentRank = gameboard[chosen.getxLoc()][chosen.getyLoc()].getRank();
                        if(opponentRank > chosen.getRank()){
                            chosen.setStatus(false);
                            gameboard[chosen.getxLoc()][chosen.getyLoc()] = null;
                            return true;
                        }
                        else if(opponentRank < chosen.getRank()){
                            gameboard[chosen.getxLoc()][chosen.getyLoc()].setStatus(false);
                            gameboard[chosen.getxLoc()][chosen.getyLoc()] = null;
                            chosen.setxLoc(chosen.getxLoc() + 1);
                            gameboard[chosen.getxLoc()][chosen.getyLoc()] = chosen;
                            return true;
                        }
                        else{ //equal rank, attacker wins
                            gameboard[chosen.getxLoc()][chosen.getyLoc()].setStatus(false);
                            gameboard[chosen.getxLoc()][chosen.getyLoc()] = null;
                            chosen.setxLoc(chosen.getxLoc() + 1);
                            gameboard[chosen.getxLoc()][chosen.getyLoc()] = chosen;
                            return true;
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


    public boolean isMinerAttack(int unitRank){
        if(unitRank == Unit.MINER) {
            return true;
        }
        else{
            return false;
        }
    }//isMinerAttack

}//StrategoGameState

