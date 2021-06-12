package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.controllers.SceneSwitchController;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        SceneSwitchController.setPrimaryStage(primaryStage);
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view/welcomeView.fxml")));
            Scene welcomeScene = new Scene(root);
            welcomeScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("css/welcomeView.css")).toExternalForm());

            primaryStage.setTitle("PokeFight");
            primaryStage.getIcons().add(new Image("file:resources/icons/programIcon.png"));
            primaryStage.setResizable(false);

            primaryStage.setScene(welcomeScene);
            primaryStage.show();
        } catch (Throwable e) {
            SceneSwitchController.handleException(e);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
