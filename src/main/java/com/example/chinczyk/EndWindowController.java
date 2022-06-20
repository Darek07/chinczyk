package com.example.chinczyk;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class EndWindowController implements Initializable {

	@FXML public Label endLabel;
	@FXML public Button submitButton;

	public void setWinner(String name) {
		endLabel.setText(String.format("%s wins the game", name));
	}

	private void initializeSubmitButton() {
		submitButton.setOnAction(this::changeToStart);
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		initializeSubmitButton();
	}

	private void changeToStart(ActionEvent event) {
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

		try {
			URL resource = getClass().getResource("EndWindowView.fxml");
			assert resource != null;
			FXMLLoader loader = new FXMLLoader(resource);
			Parent root = loader.load();

			Scene scene = new Scene(root);
			stage.setTitle("C H I Ń C Z Y K");
			stage.setScene(scene);
			stage.setResizable(true);
			stage.show();
		} catch (IOException error) {
			error.printStackTrace();
		}
	}
}
