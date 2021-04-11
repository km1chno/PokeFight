package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view/welcomeView.fxml")));
            Scene welcomeScene = new Scene(root);
            welcomeScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("css/welcomeView.css")).toExternalForm());

            primaryStage.setTitle("PokeFight");
            primaryStage.setScene(welcomeScene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
