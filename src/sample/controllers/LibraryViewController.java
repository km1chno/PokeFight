package sample.controllers;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.TilePane;
import sample.components.SinglePokemonTypeTileControl;
import sample.model.datamodels.PokemonTypeList;
import sample.model.providers.PokemonTypeListProvider;

import java.net.URL;
import java.util.ResourceBundle;

public class LibraryViewController implements Initializable {
    private int NUMBER_OF_POKEMONS;
    private PokemonTypeList pokemonList;

    @FXML
    ScrollPane scrollPane;
    @FXML
    TilePane tilePane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pokemonList = PokemonTypeListProvider.getData();
        NUMBER_OF_POKEMONS = pokemonList.getCount();
        for (int i = 0; i < NUMBER_OF_POKEMONS; i++)
            tilePane.getChildren().add(new SinglePokemonTypeTileControl(i+1, pokemonList.getName(i), pokemonList.getUrl(i)));
    }
}