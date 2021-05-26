package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import javafx.event.ActionEvent;

public class FetchFailViewController {
    @FXML
    AnchorPane anchorPane;

    @FXML
    Label errorMessage;

    @FXML
    Button goBackButton;

    public void onGoBackButtonClick(ActionEvent event) {
        SceneSwitchController.goHome(event);
    }
}
