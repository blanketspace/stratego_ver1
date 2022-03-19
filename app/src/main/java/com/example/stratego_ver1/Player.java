package com.example.stratego_ver1;

//make abstract later
public class Player {

    private Unit[] myPieces;
    private boolean myTurn;
    private boolean precedence; //am i p1 or p2? t = p1, f = p2

    /**
     * DELETE THIS PLAYER CLASS
     */

    public Player(){

    }
}

/**
 * AI STUFF
 * Select piece, choose direction, and then actually move piece
 *
 * FOR SMART AI
 * Set up strategies (random number to choose which is used) (in arrays or smth?)
 * Check flanks for opponents (mr radar)
 * Can move scout more than one space?
 *
 * */