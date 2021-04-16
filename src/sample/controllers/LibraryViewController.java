package sample.controllers;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.TilePane;
import sample.components.SinglePokemonTypeTileControl;
import sample.model.datamodels.PokemonType;
import sample.model.fetchers.PokemonTypeFetcher;
import java.net.URL;
import java.util.ResourceBundle;

public class LibraryViewController implements Initializable {
    static private final int NUMBER_OF_POKEMONS = 5;

    @FXML
    ScrollPane scrollPane;
    @FXML
    TilePane tilePane;

    PokemonType[] pokemons = new PokemonType[NUMBER_OF_POKEMONS];

    public void updateLibrary() {
        for (int i = 0; i < NUMBER_OF_POKEMONS; i++)
            tilePane.getChildren().add(new SinglePokemonTypeTileControl(pokemons[i]));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Task<Void> fetchPokemons = new Task<>() {
            @Override
            public Void call() {
                for (int i = 0; i < NUMBER_OF_POKEMONS; i++)
                    pokemons[i] = (PokemonType) new PokemonTypeFetcher().fetchAndParse("https://pokeapi.co/api/v2/pokemon/" + (i + 1));
                return null;
            }
        };
        fetchPokemons.setOnSucceeded(workerStateEvent -> updateLibrary());

        Thread thread = new Thread(fetchPokemons);
        thread.setDaemon(true);
        thread.start();
    }
}