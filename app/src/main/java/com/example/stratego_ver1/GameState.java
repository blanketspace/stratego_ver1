package com.example.stratego_ver1;

import java.util.ArrayList;
import java.util.Arrays;

public class GameState {
    /**
     * RENAME THIS CLASS TO: strategoGameState extends GameState
     * (this will be the subclass of the GameState class)
     */

    private int whoseTurn; //id of player whose turn it is

    private Unit[][] gameboard; // do not use ints as the type of this board (bc of color of Units, Unit type is needed)
    private int roundNumber; //will be initialized to zero, changed to indicate who's turn it is
    private double timeElapsed; //for the timer
    private ArrayList<Unit> p1Graveyard;
    private ArrayList<Unit> p2Graveyard;

    /**
     * ctor
     *
     * defines the state of the game on startup
     *
     * IMPORTANT: we just want to have the actions work, not really know who is player 1 or who's player 2
     * because we don't really care about that, we just want to know if the pieces
     * are owned by different/similar players so that the actions work (move, select, etc.)
     * we got this team!
     *
     */
    public GameState(){
        //no need for parameters
        //blank board with full graveyard
        gameboard = new Unit[10][10];
        p1Graveyard = new ArrayList<>(10);
        p2Graveyard = new ArrayList<>(10);
        roundNumber = 0;
    }

    /**
     * copy ctor
     *
     * @param orig
     * @Override
     *
     * the game state would incorporate with the Unit class, similar to the Hotel
     * class from the CS301 example
     */
    public GameState(GameState orig){


    }

    //TODO: rewrite to make look better
    @Override
    public String toString() {
        return "GameState{" +
                ", gameboard=" + Arrays.toString(gameboard) +
                ", roundNumber=" + roundNumber +
                ", timeElapsed=" + timeElapsed +
                '}';
    }


    /**
     * handles a 1-step move of a piece provided it is legal this object
     * is updated to reflect that move
     *
     * @param playerId id of the player that is moving the piece
     * @param startX  x-coord of the piece
     * @param startY  y-coord
     * @param dir direction (up/down/left/right) of movement
     *
     * @return false if the move is illegal and true otherwise.
     */
    public boolean movePiece(int playerId, int startX, int startY, int dir) {

    }

    /**
     * places the piece from the graveyard to the board
     * @param playerId
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @return
     */
    public boolean placePiece(int playerId, int startX, int startY, int endX, int endY){

    }



    /**
     *
     * @param playerId id of the player that is moving the piece
     * @return false if the chosen piece is not the player's piece and true otherwise
     *
     * important: we also need to know the piece owner's ID so that it knows if it's the player's
     * piece (maybe add something extra in the parameters)
     */
    public boolean choosePiece(int playerId){

    }
}
