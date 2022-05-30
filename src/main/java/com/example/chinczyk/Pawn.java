package com.example.chinczyk;

import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

public class Pawn extends Circle {

    public enum PawnType {RED, GREEN, YELLOW, BLUE}

    private Position position;
    private PawnType type;
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
        if (this.getId().startsWith("blue")) { this.type = PawnType.BLUE; }
        else if (this.getId().startsWith("green")) { this.type = PawnType.GREEN; }
        else if (this.getId().startsWith("red")) { this.type = PawnType.RED; }
        else if (this.getId().startsWith("yellow")) { this.type = PawnType.YELLOW; }
    }

    public void move() {
        position.change(type);
        GridPane.setColumnIndex(this, position.getCol());
        GridPane.setRowIndex(this, position.getRow());
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }
}
