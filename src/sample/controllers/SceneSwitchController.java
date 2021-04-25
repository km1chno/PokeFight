package sample.controllers;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import sample.controllers.pokemonDetailsControllers.SinglePokemonDetailsController;
import sample.model.providers.PokemonTypeListProvider;

import java.io.IOException;
import java.util.Objects;

public class SceneSwitchController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private void escapeToView(Stage window, String name) {
        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                try {
                    switchToView(window, name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void switchToView(ActionEvent event, String name) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/" + name + ".fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../css/" + name + ".css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToView(Stage window, String name) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/" + name + ".fxml")));
        stage = window;
        scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../css/" + name + ".css")).toExternalForm());

        stage.setScene(scene);
        stage.show();
    }

    public void switchToSinglePokemonDetails(ActionEvent event, String url) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../view/pokemonDetailsViews/singlePokemonDetailsView.fxml")));
        root = (Parent) loader.load();
        SinglePokemonDetailsController pokemonDetailsController = loader.getController();
        pokemonDetailsController.setPokemon(url);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../css/singlePokemonDetailsView.css")).toExternalForm());

        stage.setScene(scene);
        stage.show();
    }

    public void switchToLibraryView(ActionEvent event) throws IOException {
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        switchToView(event, "loadingView");

        Task<Void> fetchPokemons = new Task<>() {
            @Override
            public Void call() {
                PokemonTypeListProvider.getData();
                return null;
            }
        };

        fetchPokemons.setOnSucceeded(workerStateEvent -> {
            try {
                switchToView(window, "libraryView");
                escapeToView(window, "welcomeView");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Thread thread = new Thread(fetchPokemons);
        thread.setDaemon(true);
        thread.start();
    }
}
