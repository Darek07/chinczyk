package com.example.chinczyk;

import java.util.Set;

public class Player {

    private final Set<Pawn> pawns;
    private boolean isPlayerTurn;

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
