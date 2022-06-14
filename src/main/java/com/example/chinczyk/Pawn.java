package com.example.chinczyk;

import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

public class Pawn extends Circle {

    public enum PawnColor {RED, GREEN, YELLOW, BLUE}

    private Position position;
    private Circle yardPosition;
    private PawnColor pawnColor;
    private boolean canMove;

    public int getHowManySteps() {
        return howManySteps;
    }

    public void setHowManySteps(int howManySteps) {
        this.howManySteps = howManySteps;
    }

    private int howManySteps;

    public Pawn() {
        this.canMove = false;
    }

    public void initialize() {
        howManySteps=0;
        int column = GridPane.getColumnIndex(this);
        int row = GridPane.getRowIndex(this);
        this.position = new Position(column, row);
        identifyPawnColor();
    }

    private void identifyPawnColor() {
        String id = this.getId();
        if (id.startsWith("blue")) { this.pawnColor = PawnColor.BLUE; }
        else if (id.startsWith("green")) { this.pawnColor = PawnColor.GREEN; }
        else if (id.startsWith("red")) { this.pawnColor = PawnColor.RED; }
        else if (id.startsWith("yellow")) { this.pawnColor = PawnColor.YELLOW; }
    }


    public void move() {
        if(!canMove)
        {
            return;
        }
        position.makeStep(pawnColor);
        GridPane.setColumnIndex(this, position.getCol());
        GridPane.setRowIndex(this, position.getRow());
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public Position getPosition() {
        return position;
    }

    public Pawn setYard(Circle yardCircle) {
        this.yardPosition = yardCircle;

        return this;
    }

    public void moveToYard() {
        assert yardPosition != null;

        int column = GridPane.getColumnIndex(this.yardPosition);
        int row = GridPane.getRowIndex(this.yardPosition);

        GridPane.setColumnIndex(this, column);
        GridPane.setRowIndex(this, row);
    }
}
