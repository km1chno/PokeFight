package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.controllers.switchControllers.BasicSwitchController;

public class ArenaViewController {
    @FXML
    Label arenaTitleLabel;
    @FXML
    Button menuButton;

    public void menuButtonOnClick (ActionEvent event) {
        try {
            new BasicSwitchController("welcomeView").switchTo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
