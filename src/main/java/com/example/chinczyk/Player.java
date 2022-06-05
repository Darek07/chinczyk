package com.example.chinczyk;

import java.util.Set;

public class Player {

    private final Set<Pawn> pawns;
    private boolean isPlayerTurn;

    private int pawnsAtHome=0;

    public void countPawnsAtHome(){
        pawns.forEach(pawn -> {if(pawn.getAtHome()) pawnsAtHome++;});
    }

    public boolean winner() {
        return pawns.stream().allMatch(Pawn::getAtHome);
    }

    public Player(Set<Pawn> pawns) {
        this.pawns = pawns;
    }

    public void setPlayerTurn(boolean playerTurn) {
        isPlayerTurn = playerTurn;
        if (isPlayerTurn) {
            // todo set dependence from points
            pawns.forEach(pawn -> pawn.setCanMove(true));
        } else {
            pawns.forEach(pawn -> pawn.setCanMove(false));
        }
    }
}
