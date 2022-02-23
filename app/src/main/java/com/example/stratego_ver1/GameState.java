package com.example.stratego_ver1;

import java.util.ArrayList;
import java.util.Arrays;

public class GameState {

    private Player playerOne;
    private Player playerTwo;

    private Unit[][] gameboard;
    private int roundNumber; //will be initialized to zero, changed to indicate who's turn it is
    private double timeElapsed; //for the timer
    private ArrayList<Unit> p1Graveyard;
    private ArrayList<Unit> p2Graveyard;

    /**
     * ctor
     *
     * defines the state of the game on startup
     *
     */
    public GameState(Player p1, Player p2, Unit[][] initBoard){
        playerOne = p1;
        playerTwo = p2;
        gameboard = initBoard;
        p1Graveyard = new ArrayList<>(10);
        p2Graveyard = new ArrayList<>(10);
        roundNumber = 0;
    }

    /**
     * copy ctor
     *
     * @param orig
     * @Override
     */
    public GameState(GameState orig){
        playerOne = new Player(//state of p1);
        playerTwo = new Player(//state of p2);

    }

    //TODO: rewrite to make look better
    @Override
    public String toString() {
        return "GameState{" +
                "playerOne=" + playerOne +
                ", playerTwo=" + playerTwo +
                ", gameboard=" + Arrays.toString(gameboard) +
                ", roundNumber=" + roundNumber +
                ", timeElapsed=" + timeElapsed +
                '}';
    }
}
