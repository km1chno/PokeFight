package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class WelcomeViewController {
    @FXML
    Label welcomeMainLabel;

    @FXML
    Button welcomeLibraryButton;

    @FXML
    Button welcomeArenaButton;

    SceneSwitchController sceneController = new SceneSwitchController();

    public void welcomeLibraryButtonOnClick(javafx.event.ActionEvent actionEvent) {
        try {
            sceneController.switchToView(actionEvent, "libraryView");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void welcomeArenaButtonOnClick(javafx.event.ActionEvent actionEvent) {
        try {
            sceneController.switchToView(actionEvent, "arenaView");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
