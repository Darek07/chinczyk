package com.example.chinczyk;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.*;
import java.util.stream.Stream;

import com.example.chinczyk.Pawn.PawnColor;

public class Controller implements Initializable {

    @FXML private Circle blue_domek1;
    @FXML private Circle blue_domek2;
    @FXML private Circle blue_domek3;
    @FXML private Circle blue_domek4;

    @FXML private Circle blue_schowek2;
    @FXML private Circle blue_schowek3;
    @FXML private Circle blue_schowek4;
    @FXML private Circle blue_schwek1;

    @FXML private Circle blue_start;

    @FXML private Circle green_domek1;
    @FXML private Circle green_domek2;
    @FXML private Circle green_domek3;
    @FXML private Circle green_domek4;

    @FXML private Circle green_schowek1;
    @FXML private Circle green_schowek2;
    @FXML private Circle green_schowek3;
    @FXML private Circle green_schowek4;

    @FXML private Circle green_start;

    @FXML private Circle red_domek1;
    @FXML private Circle red_domek2;
    @FXML private Circle red_domek3;
    @FXML private Circle red_domek4;

    @FXML private Circle red_schowek1;
    @FXML private Circle red_schowek2;
    @FXML private Circle red_schowek3;
    @FXML private Circle red_schowek4;

    @FXML private Circle red_start;

    @FXML private Circle yellow_domek1;
    @FXML private Circle yellow_domek2;
    @FXML private Circle yellow_domek3;
    @FXML private Circle yellow_domek4;

    @FXML private Circle yellow_schowek1;
    @FXML private Circle yellow_schowek2;
    @FXML private Circle yellow_schowek3;
    @FXML private Circle yellow_schowek4;

    @FXML private Circle yellow_start;

    @FXML private Pawn blue_pawn1;
    @FXML private Pawn blue_pawn2;
    @FXML private Pawn blue_pawn3;
    @FXML private Pawn blue_pawn4;

    @FXML private Pawn green_pawn1;
    @FXML private Pawn green_pawn2;
    @FXML private Pawn green_pawn3;
    @FXML private Pawn green_pawn4;

    @FXML private Pawn red_pawn1;
    @FXML private Pawn red_pawn2;
    @FXML private Pawn red_pawn3;
    @FXML private Pawn red_pawn4;

    @FXML private Pawn yellow_pawn1;
    @FXML private Pawn yellow_pawn2;
    @FXML private Pawn yellow_pawn3;
    @FXML private Pawn yellow_pawn4;

    private static final Map<PawnColor, Circle> startCells = new HashMap<>(4);
    private static final Set<Circle> homeCells = new HashSet<>(16);

    private final Set<Pawn> blue_pionki = new HashSet<>(4);
    private final Set<Pawn> green_pionki = new HashSet<>(4);
    private final Set<Pawn> red_pionki = new HashSet<>(4);
    private final Set<Pawn> yellow_pionki = new HashSet<>(4);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startCells.put(PawnColor.BLUE, blue_start);
        startCells.put(PawnColor.GREEN, green_start);
        startCells.put(PawnColor.RED, red_start);
        startCells.put(PawnColor.YELLOW, yellow_start);
        Collections.addAll(homeCells,
                blue_domek1,blue_domek2,blue_domek3,blue_domek4,
                green_domek1,green_domek2,green_domek3,green_domek4,
                red_domek1,red_domek2,red_domek3,red_domek4,
                yellow_domek1,yellow_domek2,yellow_domek3,yellow_domek4);

        Collections.addAll(blue_pionki, blue_pawn1, blue_pawn2, blue_pawn3, blue_pawn4);
        Collections.addAll(green_pionki, green_pawn1, green_pawn2, green_pawn3, green_pawn4);
        Collections.addAll(red_pionki, red_pawn1, red_pawn2, red_pawn3, red_pawn4);
        Collections.addAll(yellow_pionki, yellow_pawn1, yellow_pawn2, yellow_pawn3, yellow_pawn4);

        Stream.of(blue_pionki, green_pionki, red_pionki, yellow_pionki)
                .flatMap(Collection::stream)
                .forEach(Pawn::initialize);
    }

    public Set<Pawn> getPawnsByType(PawnColor type) {
        return switch (type) {
            case BLUE -> blue_pionki;
            case GREEN -> green_pionki;
            case RED -> red_pionki;
            case YELLOW -> yellow_pionki;
        };
    }

    public static Map<PawnColor, Circle> getStartCells() {
        return startCells;
    }

    public static Set<Circle> getHomeCells() {
        return homeCells;
    }
}
