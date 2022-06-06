package com.example.chinczyk;

import java.util.Set;

public class Player {

    private final Set<Pawn> pawns;
    private boolean isPlayerTurn;
    private Pawn.PawnColor color;


    private int dice_steps;




    public Player(Pawn.PawnColor color, Set<Pawn> pawns) {
        this.pawns = pawns;
        this.color = color;
        for (Pawn pawn: this.pawns) {
            pawn.setOnMousePressed(mouseEvent -> {
                for (int i=0;i<this.dice_steps;i++) {
                    pawn.move();
                }
                setPawnsCanMove(false);
            });
        }
    }



    public void setPlayerTurn(boolean playerTurn) {
        isPlayerTurn = playerTurn;

    }

    public boolean isAnyOnBoard()
    {
        for (Pawn pawn: pawns) {
            if(pawn.getPosition().getCellType()== Position.CellType.START || pawn.getPosition().getCellType()== Position.CellType.DEFAULT)
            {
                return true;
            }
        }
        return false;
    }

    public boolean isOnBoard(Pawn pawn)
    {
        return pawn.getPosition().getCellType() == Position.CellType.START || pawn.getPosition().getCellType() == Position.CellType.DEFAULT;
    }

    public void setPawnsCanMove(boolean canMove)
    {
        if (isPlayerTurn && canMove) {
            pawns.forEach(pawn -> pawn.setCanMove(true));
            if (dice_steps != 6) {
                for (Pawn pawn : pawns) {
                    if (isOnBoard(pawn)) {
                        pawn.setCanMove(true);
                    }
                }
            } else {
                pawns.forEach(pawn -> pawn.setCanMove(true));
            }
        } else {
            pawns.forEach(pawn -> pawn.setCanMove(false));
        }
    }

    public void setDiceSteps(int dice_steps) {
        this.dice_steps = dice_steps;
    }

    public Pawn.PawnColor getPawnColor() {
        return this.color;
    }

    public Set<Pawn> getPawns() {
        return this.pawns;
    }
}
