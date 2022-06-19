package com.example.chinczyk;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.*;
import java.util.stream.Stream;

import com.example.chinczyk.Pawn.PawnColor;
import javafx.scene.shape.Polyline;

public class Controller implements Initializable {

    @FXML private Polyline blueArrow;
    @FXML private Polyline yellowArrow;
    @FXML private Polyline redArrow;
    @FXML private Polyline greenArrow;

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

    @FXML
    private ImageView diceImage;
    private Dice dice;
    public int count_steps;
    private static int countPlayerMoveInARow = 0;
    private boolean isCollisionHappened;

    @FXML
    public void pressDice() {
        if (activePlayer.isPlayerStillMove()) {
            return;
        }
        isCollisionHappened = false;
        runCollisionCheckAndCleanup();

        int nextPlayer = players.indexOf(activePlayer);
        if (count_steps != 6 && !isCollisionHappened) {
            nextPlayer = (nextPlayer + 1) % playersNumber;
        }
        count_steps=dice.roll(diceImage);

        if (!activePlayer.isThrowsToGoOut() && countPlayerMoveInARow >= 3) {
            activePlayer.setThrowsToGoOut(true);
            countPlayerMoveInARow = 0;
            setPlayerAsActive(players.get(nextPlayer));
        }

        if(!activePlayer.isThrowsToGoOut()){
            countPlayerMoveInARow++;
            if(count_steps==6)
            {
                activePlayer.setThrowsToGoOut(true);
                countPlayerMoveInARow = 0;
            }
        }
        else
        {
            setPlayerAsActive(players.get(nextPlayer));
            if(count_steps!=6  && !activePlayer.isAnyOnBoard())
            {
                return;
            }
            countPlayerMoveInARow = 0;
        }
        activePlayer.setDiceSteps(count_steps);
        activePlayer.setPawnsCanMove(true);

        if(activePlayer.checkTheWinner())
        {
            System.exit(1);
        }
    }



    private static final Map<PawnColor, Circle> startCells = new HashMap<>(4);
    private static final Set<Circle> homeCells = new HashSet<>(16);
    private static final Map<PawnColor, Set<Circle>> yardCells = new HashMap<>(4);

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
        playerTurnAnimation();
    }

    private void initializePlayers() {
        players = new ArrayList<>(4);

        Collections.addAll(players,
                new Player(PawnColor.BLUE, getPawnsByType(PawnColor.BLUE)),
                new Player(PawnColor.GREEN, getPawnsByType(PawnColor.GREEN)),
                new Player(PawnColor.YELLOW, getPawnsByType(PawnColor.YELLOW)),
                new Player(PawnColor.RED, getPawnsByType(PawnColor.RED))
        );

        setPlayerAsActive(players.get(0));
    }

    private void runCollisionCheckAndCleanup() {
        var activePawns = activePlayer.getPawns();

        for (Player player : players) {
            if (activePlayer.getPawnColor().equals(player.getPawnColor())) {
                continue;
            }
            var pawns = player.getPawns();
            pawns.forEach(pawn -> {
                var position = pawn.getPosition();

                var column = position.getCol();
                var row = position.getRow();

                if (pawn.getPosition().isAnyMatch(Position.START_CELLS.values(), new Point2D(column, row))) {
                    return;
                }

                activePawns.forEach(activePawn -> {
                    var activePawnPosition = activePawn.getPosition();
                    if (
                            column == activePawnPosition.getCol()
                                    && row == activePawnPosition.getRow()
                    ) {
                        System.out.println("Collision");
                        player.moveToYard(pawn);
                        isCollisionHappened = true;
                    }
                });
            });
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startCells.put(PawnColor.BLUE, blueStart);
        startCells.put(PawnColor.GREEN, greenStart);
        startCells.put(PawnColor.RED, redStart);
        startCells.put(PawnColor.YELLOW, yellowStart);


        yardCells.put(PawnColor.BLUE, new HashSet<>(Arrays.asList(blueYard1, blueYard2, blueYard3, blueYard4)));
        yardCells.put(PawnColor.GREEN, new HashSet<>(Arrays.asList(greenYard1, greenYard2, greenYard3, greenYard4)));
        yardCells.put(PawnColor.RED, new HashSet<>(Arrays.asList(redYard1, redYard2, redYard3, redYard4)));
        yardCells.put(PawnColor.YELLOW, new HashSet<>(Arrays.asList(yellowYard1, yellowYard2, yellowYard3, yellowYard4)));

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
        dice = new Dice();
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

    public static Map<PawnColor, Set<Circle>> getYardCells() {
        return yardCells;
    }

    private void clearTurnAnimation() {
        yellowArrow.setFill(Color.BLACK);
        redArrow.setFill(Color.BLACK);
        blueArrow.setFill(Color.BLACK);
        greenArrow.setFill(Color.BLACK);
    }

    private void playerTurnAnimation() {
        clearTurnAnimation();
        switch (activePlayer.getPawnColor()) {
            case YELLOW -> yellowArrow.setFill(Color.YELLOW);
            case RED -> redArrow.setFill(Color.RED);
            case BLUE -> blueArrow.setFill(Color.BLUE);
            case GREEN -> greenArrow.setFill(Color.GREEN);
        }
    }
}
