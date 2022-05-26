package com.example.chinczyk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Chinczyk extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Chinczyk.class.getResource("plansza_chinczyk.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 880, 880);
        stage.setTitle("C Z I N C Z Y K");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}