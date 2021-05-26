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

    private final ChangeListener<Number> onScroll = (observableValue, number, t1) -> {
        System.out.println(number + " " + t1);
        double vmid = (scrollPane.getVmin() + scrollPane.getVmax()) / 2;
        if ((double)t1 == 0 && (double)number == vmid) {
            scrollPane.setVvalue(0.5);
            return;
        }
        if ((double)t1 >= scrollPane.getVmax()) {
            if (loadedPosition == NUMBER_OF_POKEMONS) return;
            loadedPosition = Math.min(loadedPosition + NUMBER_TO_LOAD / 2 - 2, NUMBER_OF_POKEMONS);
            updateTiles();
            scrollPane.layout();
            scrollPane.setVvalue(vmid);
        }
        else if ((double)t1 <= scrollPane.getVmin()) {
            if (loadedPosition == NUMBER_TO_LOAD) return;
            loadedPosition = Math.max(loadedPosition - NUMBER_TO_LOAD / 2 + 2, NUMBER_TO_LOAD);
            updateTiles();
            scrollPane.layout();
            scrollPane.setVvalue(vmid);
        }
    };


    // Updates pokemonList with given filter and applies changes to display
    private final BiFunction<Integer, PokemonTypeListFilter, Void> updateLibraryWithFilter = (count, filter) -> {
        pokemonList = PokemonTypeListProvider.getDataWithFilter(count, filter);
        updateLibrary();
        return null;
    };

    // Initializes the LibraryView
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        filterBar = new LibraryFilterBarControl();
        filterBar.getController().setLibraryUpdateFunction(updateLibraryWithFilter);
        Utils.configureNumericTextField(filterBar.getController().filterBarCountInput);
        filterBar.setStyle("-fx-background-color: #54b66e");

        anchorPane.getChildren().add(filterBar);
        pokemonList = PokemonTypeListProvider.getData();

        updateLibrary();
    }

    // Updates NUMBER_OF_POKEMONS, NUMBER_TO_LOAD and tilePane based on pokemonList
    void updateLibrary() {
        scrollPane.vvalueProperty().removeListener(onScroll);

        NUMBER_OF_POKEMONS = pokemonList.getCount();
        NUMBER_TO_LOAD = Math.min(20, NUMBER_OF_POKEMONS);
        loadedPosition = NUMBER_TO_LOAD;
        updateTiles();
        scrollPane.setVvalue(scrollPane.getVmin());
        scrollPane.vvalueProperty().addListener(onScroll);
    }

    // Updates PokemonTiles to be displayed on screen by tilePane based on position
    void updateTiles() {
        tilePane.getChildren().clear();
        for (int i = loadedPosition - NUMBER_TO_LOAD; i < loadedPosition; i++)
            tilePane.getChildren().add(new SinglePokemonTypeTileControl(pokemonList.getId(i), pokemonList.getName(i), pokemonList.getUrl(i)));
        scrollPane.layoutYProperty();
    }
}