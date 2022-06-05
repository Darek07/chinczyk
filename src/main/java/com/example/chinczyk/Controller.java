package com.example.chinczyk;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    private Player activePlayer;
    private int playersNumber = 0;
    public void setPlayers(int playersNumber) {
        this.playersNumber = playersNumber;
    }

    private void setPlayerAsActive(Player player) {
        if (activePlayer != null) {
            activePlayer.setPlayerTurn(false);
        }

        player.setPlayerTurn(true);
        activePlayer = player;
    }

    private void initializePlayers() {
        // todo: uncomment when suport for many players will be prepared
        // players = new ArrayList<>(playersNumber);
        players = new ArrayList<>(4);

        Collections.addAll(players,
                new Player(Pawn.PawnColor.BLUE, getPawnsByType(Pawn.PawnColor.BLUE)),
                new Player(Pawn.PawnColor.GREEN, getPawnsByType(Pawn.PawnColor.GREEN)),
                new Player(Pawn.PawnColor.YELLOW, getPawnsByType(Pawn.PawnColor.YELLOW)),
                new Player(Pawn.PawnColor.RED, getPawnsByType(Pawn.PawnColor.RED))
        );

        setPlayerAsActive(players.get(0));
    }

    private void runCollisionCheckAndCleanup() {
        var activePawns = activePlayer.getPawns();

        for (Player player : players) {
            if (!activePlayer.getPawnColor().equals(player.getPawnColor())) {
                var pawns = player.getPawns();
                pawns.forEach(pawn -> {
                    var position = pawn.getPosition();

                    var column = position.getCol();
                    var row = position.getRow();

                    activePawns.forEach(activePawn -> {
                        var activePawnPosition = activePawn.getPosition();
                        if (
                                column == activePawnPosition.getCol()
                                        && row == activePawnPosition.getRow()
                        ) {
                            System.out.println("Collision");
                            pawn.moveToYard();
                        }
                    });
                });
            }
        }
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

        Collections.addAll(bluePawns,
                bluePawn1.setYard(blueYard1),
                bluePawn2.setYard(blueYard2),
                bluePawn3.setYard(blueYard3),
                bluePawn4.setYard(blueYard4)
        );
        Collections.addAll(greenPawns,
                greenPawn1.setYard(greenYard1),
                greenPawn2.setYard(greenYard2),
                greenPawn3.setYard(greenYard3),
                greenPawn4.setYard(greenYard4)
        );
        Collections.addAll(redPawns,
                redPawn1.setYard(redYard1),
                redPawn2.setYard(redYard2),
                redPawn3.setYard(redYard3),
                redPawn4.setYard(redYard4)
        );
        Collections.addAll(yellowPawns,
                yellowPawn1.setYard(yellowYard1),
                yellowPawn2.setYard(yellowYard2),
                yellowPawn3.setYard(yellowYard3),
                yellowPawn4.setYard(yellowYard4)
        );

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
