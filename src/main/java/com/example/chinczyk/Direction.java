package com.example.chinczyk;

import javafx.geometry.Point2D;

public enum Direction {
    UP(0, -1), RIGHT(1, 0), DOWN(0, 1), LEFT(-1, 0), NONE(0, 0);
    final Point2D coordination;

    Direction getNextCounterClockwise() {
        return switch (this) {
            case UP -> LEFT;
            case LEFT -> DOWN;
            case DOWN -> RIGHT;
            case RIGHT -> UP;
            default -> NONE;
        };
    }

    Direction getNextClockWise() {
        return switch (this) {
            case UP -> RIGHT;
            case RIGHT -> DOWN;
            case DOWN -> LEFT;
            case LEFT -> UP;
            default -> NONE;
        };
    }

    Direction(int x, int y) {
        this.coordination = new Point2D(x, y);
    }
}
