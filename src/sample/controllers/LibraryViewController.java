package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;
import sample.components.SinglePokemonTypeTileControl;
import sample.model.datamodels.PokemonType;
import sample.model.fetchers.PokemonTypeFetcher;

import java.net.URL;
import java.util.ResourceBundle;

public class LibraryViewController implements Initializable {
    @FXML
    TilePane tilePane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PokemonType[] pokemons = new PokemonType[10];

        for (int i = 0; i < 10; i++)
            pokemons[i] = (PokemonType) new PokemonTypeFetcher().fetchAndParse("https://pokeapi.co/api/v2/pokemon/" + (i+1));

        for (PokemonType pokemon : pokemons) {
            tilePane.getChildren().add(new SinglePokemonTypeTileControl(pokemon));
        }
    }
}
