package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.controllers.SceneSwitchController;
import sample.controllers.switchControllers.BasicSwitchController;
import sample.model.Constants;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Constants.stage = primaryStage;
        try {
            BasicSwitchController primarySwitch = new BasicSwitchController("welcomeView");

            primaryStage.setTitle("PokeFight");
            primaryStage.getIcons().add(new Image("file:resources/icons/programIcon.png"));
            primaryStage.setResizable(false);

            primarySwitch.switchTo();
        } catch (Throwable e) {
            SceneSwitchController.handleException(e);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
