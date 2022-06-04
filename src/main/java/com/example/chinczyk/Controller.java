package com.example.chinczyk;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.*;
import java.util.stream.Stream;

import com.example.chinczyk.Pawn.PawnColor;

public class Controller implements Initializable {

    @FXML private Circle blueHome1;
    @FXML private Circle blueHome2;
    @FXML private Circle blueHome3;
    @FXML private Circle blueHome4;

    @FXML private Circle blueYard1;
    @FXML private Circle blueYard2;
    @FXML private Circle blueYard3;
    @FXML private Circle blueYard4;

    @FXML private Circle blueStart;

    @FXML private Circle greenHome1;
    @FXML private Circle greenHome2;
    @FXML private Circle greenHome3;
    @FXML private Circle greenHome4;

    @FXML private Circle greenYard1;
    @FXML private Circle greenYard2;
    @FXML private Circle greenYard3;
    @FXML private Circle greenYard4;

    @FXML private Circle greenStart;

    @FXML private Circle redHome1;
    @FXML private Circle redHome2;
    @FXML private Circle redHome3;
    @FXML private Circle redHome4;

    @FXML private Circle redYard1;
    @FXML private Circle redYard2;
    @FXML private Circle redYard3;
    @FXML private Circle redYard4;

    @FXML private Circle redStart;

    @FXML private Circle yellowHome1;
    @FXML private Circle yellowHome2;
    @FXML private Circle yellowHome3;
    @FXML private Circle yellowHome4;

    @FXML private Circle yellowYard1;
    @FXML private Circle yellowYard2;
    @FXML private Circle yellowYard3;
    @FXML private Circle yellowYard4;

    @FXML private Circle yellowStart;

    @FXML private Pawn bluePawn1;
    @FXML private Pawn bluePawn2;
    @FXML private Pawn bluePawn3;
    @FXML private Pawn bluePawn4;

    @FXML private Pawn greenPawn1;
    @FXML private Pawn greenPawn2;
    @FXML private Pawn greenPawn3;
    @FXML private Pawn greenPawn4;

    @FXML private Pawn redPawn1;
    @FXML private Pawn redPawn2;
    @FXML private Pawn redPawn3;
    @FXML private Pawn redPawn4;

    @FXML private Pawn yellowPawn1;
    @FXML private Pawn yellowPawn2;
    @FXML private Pawn yellowPawn3;
    @FXML private Pawn yellowPawn4;

    private static final Map<PawnColor, Circle> startCells = new HashMap<>(4);
    private static final Set<Circle> homeCells = new HashSet<>(16);

    private final Set<Pawn> bluePawns = new HashSet<>(4);
    private final Set<Pawn> greenPawns = new HashSet<>(4);
    private final Set<Pawn> redPawns = new HashSet<>(4);
    private final Set<Pawn> yellowPawns = new HashSet<>(4);

    private List<Player> players;
    private int playersNumber = 0;
    public void setPlayers(int playersNumber) {
        this.playersNumber = playersNumber;
    }

    private void initializePlayers() {
        // todo: uncomment when suport for many players will be prepared
        // players = new ArrayList<>(playersNumber);
        players = new ArrayList<>(4);

        Collections.addAll(players,
            new Player(getPawnsByType(Pawn.PawnColor.BLUE)),
            new Player(getPawnsByType(Pawn.PawnColor.GREEN)),
            new Player(getPawnsByType(Pawn.PawnColor.YELLOW)),
            new Player(getPawnsByType(Pawn.PawnColor.RED))
        );
        players.get(0).setPlayerTurn(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startCells.put(PawnColor.BLUE, blueStart);
        startCells.put(PawnColor.GREEN, greenStart);
        startCells.put(PawnColor.RED, redStart);
        startCells.put(PawnColor.YELLOW, yellowStart);
        Collections.addAll(homeCells,
                blueHome1, blueHome2, blueHome3, blueHome4,
                greenHome1, greenHome2, greenHome3, greenHome4,
                redHome1, redHome2, redHome3, redHome4,
                yellowHome1, yellowHome2, yellowHome3, yellowHome4);

        Collections.addAll(bluePawns, bluePawn1, bluePawn2, bluePawn3, bluePawn4);
        Collections.addAll(greenPawns, greenPawn1, greenPawn2, greenPawn3, greenPawn4);
        Collections.addAll(redPawns, redPawn1, redPawn2, redPawn3, redPawn4);
        Collections.addAll(yellowPawns, yellowPawn1, yellowPawn2, yellowPawn3, yellowPawn4);

        Stream.of(bluePawns, greenPawns, redPawns, yellowPawns)
                .flatMap(Collection::stream)
                .forEach(Pawn::initialize);

        initializePlayers();
    }

    public Set<Pawn> getPawnsByType(PawnColor type) {
        return switch (type) {
            case BLUE -> bluePawns;
            case GREEN -> greenPawns;
            case RED -> redPawns;
            case YELLOW -> yellowPawns;
        };
    }

    public static Map<PawnColor, Circle> getStartCells() {
        return startCells;
    }

    public static Set<Circle> getHomeCells() {
        return homeCells;
    }
}
