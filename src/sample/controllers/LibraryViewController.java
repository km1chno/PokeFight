package sample.controllers;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import sample.components.LibraryFilterBarControl;
import sample.components.SinglePokemonTypeTileControl;
import sample.model.Utils;
import sample.model.datamodels.PokemonTypeList;
import sample.model.providers.PokemonTypeListFilter;
import sample.model.providers.PokemonTypeListProvider;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.BiFunction;

public class LibraryViewController implements Initializable {
    private int NUMBER_OF_POKEMONS; // The number of all the fetched pokemons
    private int NUMBER_TO_LOAD; // The number of all pokemons displayed on screen
    private PokemonTypeList pokemonList; // List containing all of the fetched pokemons
    private LibraryFilterBarControl filterBar; // Bar object responsible for user interaction with filtering
    private int loadedPosition; // Index of last element displayed by tilePane in pokemonList

    @FXML
    ScrollPane scrollPane; // Container allowing for scrolling of content

    @FXML
    TilePane tilePane; // Container for PokemonTiles

    @FXML
    AnchorPane anchorPane;

    // Updates pokemonList with given filter and applies changes to display
    private final BiFunction<Integer, PokemonTypeListFilter, Void> updateLibraryWithFilter = (count, filter) -> {
        pokemonList = PokemonTypeListProvider.getDataWithFilter(count, filter);
        updateLibrary();
        return null;
    };

    // Initializes the LibraryView and adds scrollListener
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        filterBar = new LibraryFilterBarControl();
        filterBar.getController().setLibraryUpdateFunction(updateLibraryWithFilter);
        Utils.configureNumericTextField(filterBar.getController().filterBarCountInput);
        filterBar.setStyle("-fx-background-color: #54b66e");

        anchorPane.getChildren().add(filterBar);
        pokemonList = PokemonTypeListProvider.getData();

        updateLibrary();
        scrollPane.vvalueProperty().addListener((observableValue, number, t1) -> {
            System.out.println(number + " " + t1);
            if ((double)t1 >= scrollPane.getVmax()) {
                loadedPosition = Math.min(loadedPosition + NUMBER_TO_LOAD, NUMBER_OF_POKEMONS);
                addTiles();
            }
        });
    }

    // Updates NUMBER_OF_POKEMONS, NUMBER_TO_LOAD and tilePane based on pokemonList
    void updateLibrary() {
        NUMBER_OF_POKEMONS = pokemonList.getCount();
        NUMBER_TO_LOAD = Math.min(20, NUMBER_OF_POKEMONS);
        loadedPosition = NUMBER_TO_LOAD;
        updateTiles();
        scrollPane.setVvalue(scrollPane.getVmin());
    }

    // Increases number of PokemonTiles displayed by tilePane
    void addTiles() {
        while (tilePane.getChildren().size() < loadedPosition)
            tilePane.getChildren().add(new SinglePokemonTypeTileControl(pokemonList.getId(tilePane.getChildren().size()), pokemonList.getName(tilePane.getChildren().size()), pokemonList.getUrl(tilePane.getChildren().size())));
    }

    // Reloads PokemonTiles to be displayed on screen by tilePane based on position
    void updateTiles() {
        tilePane.getChildren().clear();
        addTiles();
    }
}