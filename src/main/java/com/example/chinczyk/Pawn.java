package com.example.chinczyk;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

public class Pawn extends Circle {

    public enum PawnColor {RED, GREEN, YELLOW, BLUE}

    private Position position;
    private PawnColor pawnColor;
    private boolean canMove;

    public Pawn() {
        this.canMove = false;
        setOnMousePressed(event -> {
            if (canMove) {
                move();
            }
        });
    }

    public void initialize() {
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
        if (position.isAtHome())    {
            canMove=false;
            return;
        }
        position.makeStep(pawnColor);
        GridPane.setColumnIndex(this, position.getCol());
        GridPane.setRowIndex(this, position.getRow());
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public boolean getAtHome() {
        return position.isAtHome();
    }
}
