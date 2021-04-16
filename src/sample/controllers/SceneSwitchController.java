package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.model.datamodels.PokemonType;

import java.io.IOException;
import java.util.Objects;

public class SceneSwitchController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToView(ActionEvent event, String name) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/" + name + ".fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../css/" + name + ".css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSinglePokemonDetails(ActionEvent event, PokemonType pokemon) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../view/singlePokemonDetailsView.fxml")));
        root = (Parent) loader.load();
        SinglePokemonDetailsController pokemonDetailsController = loader.getController();
        pokemonDetailsController.setPokemon(pokemon);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../css/singlePokemonDetailsView.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
