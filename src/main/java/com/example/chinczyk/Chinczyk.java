package com.example.chinczyk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Chinczyk extends Application {

    private List<Player> players = new ArrayList<>(4);

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Chinczyk.class.getResource("plansza_chinczyk.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 960, 960);
        stage.setTitle("C Z I N C Z Y K");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
        Controller controller = fxmlLoader.getController();
        Collections.addAll(players,
                new Player(controller.getPawnsByType(Pawn.PawnColor.BLUE)),
                new Player(controller.getPawnsByType(Pawn.PawnColor.GREEN)),
                new Player(controller.getPawnsByType(Pawn.PawnColor.YELLOW)),
                new Player(controller.getPawnsByType(Pawn.PawnColor.RED)));
        players.get(0).setPlayerTurn(true);
    }

    public static void main(String[] args) {
        launch();
    }
}