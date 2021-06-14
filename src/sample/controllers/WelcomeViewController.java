package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.controllers.switchControllers.LoadingSwitchController;

import java.io.IOException;

public class WelcomeViewController {
    @FXML
    Label welcomeMainLabel;

    @FXML
    Button welcomeLibraryButton;

    @FXML
    Button welcomeArenaButton;

    public void welcomeLibraryButtonOnClick(javafx.event.ActionEvent actionEvent) {
        try {
            new LoadingSwitchController("libraryView").switchTo();
        } catch (Throwable e) {
            SceneSwitchController.handleException(e);
        }
    }

    public void welcomeArenaButtonOnClick(javafx.event.ActionEvent actionEvent) {
        try {
            new LoadingSwitchController("../view/fighterChooseViews/fighterChooseView.fxml", "../css/fighterChoose/fighterChooseView.css").switchTo();
        } catch (Throwable e) {
            SceneSwitchController.handleException(e);
        }
    }
}
