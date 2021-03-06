package com.example.chinczyk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Chinczyk extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        URL resource = getClass().getResource("StartWindowView.fxml");
        assert resource != null;
        Parent root = FXMLLoader.load(resource);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}