package com.example.chinczyk;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.chinczyk.Pionek.PionekType;

public class Controller implements Initializable {

    @FXML
    private Circle blue_domek1;

    @FXML
    private Circle blue_domek2;

    @FXML
    private Circle blue_domek3;

    @FXML
    private Circle blue_domek4;

    @FXML
    private Circle blue_schowek2;

    @FXML
    private Circle blue_schowek3;

    @FXML
    private Circle blue_schowek4;

    @FXML
    private Circle blue_schwek1;

    @FXML
    private Circle blue_start;

    @FXML
    private Circle green_domek1;

    @FXML
    private Circle green_domek2;

    @FXML
    private Circle green_domek3;

    @FXML
    private Circle green_domek4;

    @FXML
    private Circle green_schowek1;

    @FXML
    private Circle green_schowek2;

    @FXML
    private Circle green_schowek3;

    @FXML
    private Circle green_schowek4;

    @FXML
    private Circle green_start;

    @FXML
    private Circle red_domek1;

    @FXML
    private Circle red_domek2;

    @FXML
    private Circle red_domek3;

    @FXML
    private Circle red_domek4;

    @FXML
    private Circle red_schowek1;

    @FXML
    private Circle red_schowek2;

    @FXML
    private Circle red_schowek3;

    @FXML
    private Circle red_schowek4;

    @FXML
    private Circle red_start;

    @FXML
    private Circle yellow_domek1;

    @FXML
    private Circle yellow_domek2;

    @FXML
    private Circle yellow_domek3;

    @FXML
    private Circle yellow_domek4;

    @FXML
    private Circle yellow_schowek1;

    @FXML
    private Circle yellow_schowek2;

    @FXML
    private Circle yellow_schowek3;

    @FXML
    private Circle yellow_schowek4;

    @FXML
    private Circle yellow_start;

    @FXML private Circle blue_pionek1;
    @FXML private Circle blue_pionek2;
    @FXML private Circle blue_pionek3;
    @FXML private Circle blue_pionek4;

    @FXML private Circle green_pionek1;
    @FXML private Circle green_pionek2;
    @FXML private Circle green_pionek3;
    @FXML private Circle green_pionek4;

    @FXML private Circle red_pionek1;
    @FXML private Circle red_pionek2;
    @FXML private Circle red_pionek3;
    @FXML private Circle red_pionek4;

    @FXML private Circle yellow_pionek1;
    @FXML private Circle yellow_pionek2;
    @FXML private Circle yellow_pionek3;
    @FXML private Circle yellow_pionek4;

    @FXML public GridPane gridPane;

    private final Set<Circle> blue_pionki = new HashSet<>(4);
    private final Set<Circle> green_pionki = new HashSet<>(4);
    private final Set<Circle> red_pionki = new HashSet<>(4);
    private final Set<Circle> yellow_pionki = new HashSet<>(4);
    private PionekType turn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Collections.addAll(blue_pionki, blue_pionek1, blue_pionek2, blue_pionek3, blue_pionek4);
        Collections.addAll(green_pionki, green_pionek1, green_pionek2, green_pionek3, green_pionek4);
        Collections.addAll(red_pionki, red_pionek1, red_pionek2, red_pionek3, red_pionek4);
        Collections.addAll(yellow_pionki, yellow_pionek1, yellow_pionek2, yellow_pionek3, yellow_pionek4);

        blue_pionki.forEach(p->new Pionek(GridPane.getColumnIndex(p), GridPane.getRowIndex(p), PionekType.BLUE));
        green_pionki.forEach(p->new Pionek(GridPane.getColumnIndex(p), GridPane.getRowIndex(p), PionekType.GREEN));
        red_pionki.forEach(p->new Pionek(GridPane.getColumnIndex(p), GridPane.getRowIndex(p), PionekType.RED));
        yellow_pionki.forEach(p->new Pionek(GridPane.getColumnIndex(p), GridPane.getRowIndex(p), PionekType.YELLOW));

        turn = PionekType.BLUE;
    }

    @FXML
    public void movePionek() {
        Set<Circle> cp = switch (turn) {
            case BLUE -> blue_pionki;
            case GREEN -> green_pionki;
            case RED -> red_pionki;
            case YELLOW -> yellow_pionki;
        };

        Circle pressedPionek = cp.stream().filter(Node::isPressed).findAny().orElse(null);
        Pionek.move(gridPane, pressedPionek, turn);
    }
}
