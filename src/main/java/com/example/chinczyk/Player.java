package com.example.chinczyk;

import java.util.Set;

import static com.example.chinczyk.Position.CellType.HOME;
import static com.example.chinczyk.Position.CellType.START;

public class Player {

    private final Set<Pawn> pawns;
    private boolean isPlayerTurn;
    private Pawn.PawnColor color;
    private boolean throwsToGoOut;
    private int dice_steps;
    private int pawnAtHome;
    private boolean isTheWinner;




    public Player(Pawn.PawnColor color, Set<Pawn> pawns) {
        this.pawns = pawns;
        this.color = color;
        this.isTheWinner=false;
        this.dice_steps=0;
        this.pawnAtHome=0;
        this.throwsToGoOut=false;
        for (Pawn pawn: this.pawns) {
            pawn.setOnMousePressed(mouseEvent -> {
                for (int i=0;i<pawn.getHowManySteps();i++) {
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
            if(pawn.getPosition().getCellType()== START || pawn.getPosition().getCellType()== Position.CellType.DEFAULT)
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

    public boolean checkTheWinner()
    {
        if(pawnAtHome==4)
        {
            this.isTheWinner=true;
            return true;
        }
        return false;
    }

    public void addPawnsAtHome()
    {
        this.pawnAtHome+=1;
    }

    public void setPawnsCanMove(boolean canMove)
    {
        if (isPlayerTurn && canMove) {
             pawns.forEach(pawn -> pawn.setCanMove(true));
            //System.out.println(dice_steps);
            if (dice_steps != 6) {
                for (Pawn pawn : pawns) {
                    if (isOnBoard(pawn)) {
                        pawn.setCanMove(true);
                        pawn.setHowManySteps(dice_steps);
                    }
                    else
                    {
                        pawn.setCanMove(false);
                    }
                }
            } else {
                for(Pawn pawn : pawns)
                {
                    if(!isOnBoard(pawn))
                    {
                        pawn.setHowManySteps(1);
                        pawn.setCanMove(true);
                    }
                    else
                    {
                        pawn.setHowManySteps(6);
                        pawn.setCanMove(true);
                    }
                }
            }
        } else {
            pawns.forEach(pawn -> pawn.setCanMove(false));
        }

        pawnAtHome=0;
        for(Pawn pawn : pawns)
        {
            if(pawn.getPosition().getCellType()==HOME)
            {
                addPawnsAtHome();
            }
            System.out.println(pawnAtHome);
            System.out.println(pawn.getPosition().getCellType());
        }
    }

    public boolean isPlayerStillMove() {
        return pawns.stream().anyMatch(Pawn::isCanMove);
    }

    public void setDiceSteps(int dice_steps) {
        this.dice_steps = dice_steps;
    }

    public void setThrowsToGoOut(boolean throwsToGoOut) {
        this.throwsToGoOut = throwsToGoOut;
    }

    public Pawn.PawnColor getPawnColor() {
        return this.color;
    }

    public Set<Pawn> getPawns() {
        return this.pawns;
    }

    public boolean isThrowsToGoOut() {
        return throwsToGoOut;
    }

    public boolean isTheWinner() {
        return isTheWinner;
    }

    public void setTheWinner(boolean theWinner) {
        isTheWinner = theWinner;
    }


}
