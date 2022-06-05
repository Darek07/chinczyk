package com.example.chinczyk;

import java.util.Set;

public class Player {

    private final Set<Pawn> pawns;
    private boolean isPlayerTurn;
    private Pawn.PawnColor color;

    public Player(Pawn.PawnColor color, Set<Pawn> pawns) {
        this.pawns = pawns;
        this.color = color;
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

    public Pawn.PawnColor getPawnColor() {
        return this.color;
    }

    public Set<Pawn> getPawns() {
        return this.pawns;
    }
}
