package com.example.chinczyk;

// Posiada dane statyczne o każdej pozycji

import javafx.geometry.Point2D;

import java.util.*;

public class Position {

    public enum CellType {YARD, START, DEFAULT, HOME}

    public static final Point2D BLUE_START = new Point2D(6, 1);
    public static final Point2D GREEN_START = new Point2D(10, 7);
    public static final Point2D RED_START = new Point2D(0, 5);
    public static final Point2D YELLOW_START = new Point2D(4, 11);
    public static final HashSet<Point2D> START_POSITIONS = new HashSet<>(4);
    public static final HashSet<Point2D> BLUE_DOMEK = new HashSet<>(4);
    public static final HashSet<Point2D> GREEN_DOMEK = new HashSet<>(4);
    public static final HashSet<Point2D> RED_DOMEK = new HashSet<>(4);
    public static final HashSet<Point2D> YELLOW_DOMEK = new HashSet<>(4);
    public static final HashSet<HashSet<Point2D>> DOMKI = new HashSet<>(4);

    static {
        Collections.addAll(START_POSITIONS, BLUE_START, GREEN_START, RED_START, YELLOW_START);
        Collections.addAll(BLUE_DOMEK,
                new Point2D(5, 2),
                new Point2D(5, 3),
                new Point2D(5, 4),
                new Point2D(5, 5));
        Collections.addAll(GREEN_DOMEK,
                new Point2D(6, 6),
                new Point2D(7, 6),
                new Point2D(8, 6),
                new Point2D(9, 6));
        Collections.addAll(RED_DOMEK,
                new Point2D(1, 6),
                new Point2D(2, 6),
                new Point2D(3, 6),
                new Point2D(4, 6));
        Collections.addAll(YELLOW_DOMEK,
                new Point2D(5, 7),
                new Point2D(5, 8),
                new Point2D(5, 9),
                new Point2D(5, 10));
        Collections.addAll(DOMKI, BLUE_DOMEK, GREEN_DOMEK, RED_DOMEK, YELLOW_DOMEK);
    }

    private Point2D cell;
    private CellType cellType = CellType.YARD;
    private Direction dir = Direction.NONE;

    public void change(Pawn.PawnType type) {
        if (cellType == CellType.YARD) {
            switch (type) {
                case BLUE -> cell = BLUE_START;
                case GREEN -> cell = GREEN_START;
                case RED -> cell = RED_START;
                case YELLOW -> cell = YELLOW_START;
            }
        } else cell = cell.add(this.dir.coordination);
        if (START_POSITIONS.stream().anyMatch(p -> p.equals(cell))) cellType = CellType.START;
        else if (DOMKI.stream().flatMap(Collection::stream).anyMatch(p -> p.equals(cell))) cellType = CellType.HOME;
        else if (cellType != CellType.YARD) cellType = CellType.DEFAULT;
        changeDir(type);
    }

    private void changeDir(Pawn.PawnType type) {
        if (cellType == CellType.START) {
            if (cell.equals(BLUE_START)) dir = Direction.DOWN;
            else if (cell.equals(GREEN_START)) dir = Direction.LEFT;
            else if (cell.equals(RED_START)) dir = Direction.RIGHT;
            else if (cell.equals(YELLOW_START)) dir = Direction.UP;
        } else if (cellType != CellType.DEFAULT) return;

        if (DOMKI.stream().flatMap(Collection::stream).anyMatch(p -> p.equals(cell.add(this.dir.coordination))))
            dir = dir.getNextCounterClockwise();
        if (cell.equals(BLUE_START.subtract(2, 0)) ||
                cell.equals(GREEN_START.subtract(0, 2)) ||
                cell.equals(RED_START.subtract(0, -2)) ||
                cell.equals(YELLOW_START.subtract(-2, 0)))
            dir = dir.getNextClockWise();
        dir = switch (type) {
            case BLUE -> cell.add(this.dir.coordination).equals(BLUE_START);
            case GREEN -> cell.add(this.dir.coordination).equals(GREEN_START);
            case RED -> cell.add(this.dir.coordination).equals(RED_START);
            case YELLOW -> cell.add(this.dir.coordination).equals(YELLOW_START);
        } ? dir.getNextClockWise() : dir;
    }

    public Position(int col, int row) {
        this.cell = new Point2D(col, row);
    }

    public int getCol() {
        return (int) cell.getX();
    }

    public int getRow() {
        return (int) cell.getY();
    }
}
