package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneSwitchController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    /*public void switchToWelcomeView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/welcomeView.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../css/welcomeView.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToLibraryView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/libraryView.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../css/libraryView.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }*/

    // można też spróbować zrobić zmienną name w samym SceneSwitchController

    public void switchToView(ActionEvent event, String name) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/" + name + ".fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../css/" + name + ".css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
