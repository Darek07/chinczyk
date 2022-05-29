package com.example.chinczyk;

import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

import java.util.HashSet;
import java.util.Set;

public class Pionek {

    public enum PionekType {RED, GREEN, YELLOW, BLUE}

    private static Set<Pionek> pionki = new HashSet<>(16);
    private Pozycja pozycja;
    private PionekType type;

    public Pionek(int col, int row, PionekType type) {
        this.pozycja = new Pozycja(col, row);
        this.type = type;
        pionki.add(this);
    }

    public static void move(GridPane gridPane, Circle pressedPionek, PionekType type) {
        if (pressedPionek == null) return;
        Pionek pressed = pionki.stream()
                .filter(p -> p.type == type && p.pozycja.equals(
                        new Pozycja(GridPane.getColumnIndex(pressedPionek), GridPane.getRowIndex(pressedPionek))))
                .findFirst()
                .orElse(null);
        if (pressed == null) return;
        pressed.pozycja.change(pressed.type);

        gridPane.getChildren().remove(pressedPionek);
        gridPane.add(pressedPionek, pressed.pozycja.getCol(), pressed.pozycja.getRow());
    }
}
