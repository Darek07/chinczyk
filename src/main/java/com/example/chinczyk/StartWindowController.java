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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartWindowController implements Initializable {
    ToggleGroup checkboxGroup = new ToggleGroup();

    @FXML
    private RadioButton radioButtonTwoPlayers;
    @FXML
    private RadioButton radioButtonThreePlayers;
    @FXML
    private RadioButton radioButtonFourPlayers;

    @FXML
    private Button submitButton;

    @FXML
    private Label errorLabel;

    private void initializeRadioGroupButtons() {
        radioButtonTwoPlayers.setToggleGroup(checkboxGroup);
        radioButtonThreePlayers.setToggleGroup(checkboxGroup);
        radioButtonFourPlayers.setToggleGroup(checkboxGroup);

        radioButtonFourPlayers.setSelected(true);
    }

    private int getSelectedCheckboxGroupValue() {
        RadioButton selected = (RadioButton) checkboxGroup.getSelectedToggle();
        String selectedText = selected.getText();

        String fourPlayers = radioButtonFourPlayers.getText();
        String threePlayers = radioButtonThreePlayers.getText();

        if (selectedText.equals(fourPlayers)) {
            return 4;
        }

        if (selectedText.equals(threePlayers)) {
            return 3;
        }

        return 2;
    }

    private void changeToBoard(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        try {
            URL resource = getClass().getResource("ChinczykView.fxml");
            assert resource != null;
            FXMLLoader loader = new FXMLLoader(resource);
            Parent root = loader.load();

            Controller gameController = loader.getController();

            int selectedPlayersNumber = getSelectedCheckboxGroupValue();
            gameController.setPlayers(selectedPlayersNumber);

            Scene scene = new Scene(root);
            stage.setTitle("C H I Ń C Z Y K");
            stage.setScene(scene);
            stage.setResizable(true);
            stage.show();
        } catch (IOException error) {
            errorLabel.setText("Wystapil nieznany blad");
            errorLabel.setStyle("-fx-text-fill: red; -fx-font-size: 24px");
            submitButton.setDisable(true);

            error.printStackTrace();
        }
    }

    private void initializeSubmitButton() {
        submitButton.setOnAction(this::changeToBoard);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeRadioGroupButtons();
        initializeSubmitButton();
    }
}
