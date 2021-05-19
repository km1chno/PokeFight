package sample.controllers;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import sample.controllers.fightPrepControllers.FightPrepViewController;
import sample.controllers.pokemonDetailsControllers.SinglePokemonDetailsController;
import sample.model.datamodels.PokemonType;
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

    public void switchToSinglePokemonDetails(ActionEvent event, int id) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../view/pokemonDetailsViews/singlePokemonDetailsView.fxml")));
        root = (Parent) loader.load();
        SinglePokemonDetailsController pokemonDetailsController = loader.getController();
        pokemonDetailsController.setPokemon(id);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../css/pokemonDetails/singlePokemonDetailsView.css")).toExternalForm());

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

    public void switchToFighterChooseView(ActionEvent event) {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/fighterChooseViews/fighterChooseView.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../css/fighterChoose/fighterChooseView.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToFightPrepView(ActionEvent event, PokemonType left, PokemonType right) {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../view/fightPrepViews/fightPrepView.fxml")));

        try {
            root = (Parent) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FightPrepViewController fightPrepController = loader.getController();
        fightPrepController.setPokemons(left, right);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../css/fightPrep/fightPrepView.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
