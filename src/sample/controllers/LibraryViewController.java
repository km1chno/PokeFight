package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import sample.components.LibraryFilterBarControl;
import sample.components.SinglePokemonTypeTileControl;
import sample.model.datamodels.PokemonTypeList;
import sample.model.providers.PokemonTypeListFilter;
import sample.model.providers.PokemonTypeListProvider;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.BiFunction;

public class LibraryViewController implements Initializable {
    private int NUMBER_OF_POKEMONS;
    private PokemonTypeList pokemonList;
    private LibraryFilterBarControl filterBar;

    @FXML
    ScrollPane scrollPane;

    @FXML
    TilePane tilePane;

    @FXML
    AnchorPane anchorPane;

    private final BiFunction<Integer, PokemonTypeListFilter, Void> updateLibraryWithFilter = (count, filter) -> {
        pokemonList = PokemonTypeListProvider.getDataWithFilter(count, filter);
        updateLibrary();
        return null;
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        filterBar = new LibraryFilterBarControl();
        filterBar.getController().setLibraryUpdateFunction(updateLibraryWithFilter);

        anchorPane.getChildren().add(filterBar);
        pokemonList = PokemonTypeListProvider.getData();
        updateLibrary();
    }

    void updateLibrary() {
        tilePane.getChildren().clear();
        NUMBER_OF_POKEMONS = pokemonList.getCount();
        for (int i = 0; i < NUMBER_OF_POKEMONS; i++)
            tilePane.getChildren().add(new SinglePokemonTypeTileControl(pokemonList.getId(i), pokemonList.getName(i), pokemonList.getUrl(i)));
    }
}