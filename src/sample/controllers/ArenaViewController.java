package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ArenaViewController {
    @FXML
    Label arenaTitleLabel;
    @FXML
    Button menuButton;

    public void menuButtonOnClick (ActionEvent event) {
        try {
            SceneSwitchController.switchToView(event, "welcomeView");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
