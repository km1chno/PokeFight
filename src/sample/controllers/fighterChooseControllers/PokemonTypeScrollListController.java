package sample.controllers.fighterChooseControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.TilePane;
import sample.components.fighterChooseControls.PokemonTileControl;
import sample.components.fighterChooseControls.PokemonTypeScrollList;
import sample.controllers.SceneSwitchController;
import sample.model.datamodels.PokemonType;
import sample.model.datamodels.PokemonTypeList;
import sample.model.exceptions.HttpException;
import sample.model.providers.PokemonTypeListProvider;
import sample.model.providers.PokemonTypeProvider;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class PokemonTypeScrollListController {
    private Consumer<Integer> onClickFunction;
    private PokemonTypeList pokemonList;

    private Integer NUMBER_OF_POKEMONS;
    private Integer NUMBER_TO_LOAD;
    private Integer loadedPosition;

    private PokemonType chosen;

    String color;

    @FXML
    ScrollPane scrollPane;

    @FXML
    TilePane tilePane;

    @FXML
    public void initialize() throws HttpException {
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        chosen = null;

        onClickFunction = id -> {
            if (chosen != null) {
                ((PokemonTileControl) tilePane.getChildren().get(chosen.getId()-1)).deletePaneStyle(color);
            }
            ((PokemonTileControl) tilePane.getChildren().get(id-1)).addPaneStyle(color);

            try {
                chosen = PokemonTypeProvider.getData(id);
            } catch (HttpException e) {
                SceneSwitchController.handleException(e);
            }
        };

        pokemonList = PokemonTypeListProvider.getData();

        updateScrollList();
        scrollPane.vvalueProperty().addListener((observableValue, number, t1) -> {
            if ((double) t1 >= scrollPane.getVmax()) {
                loadedPosition = Math.min(loadedPosition + NUMBER_TO_LOAD, NUMBER_OF_POKEMONS);
                addTiles();
            }
        });
    }

    private void updateScrollList() {
        NUMBER_OF_POKEMONS = pokemonList.getCount();
        NUMBER_TO_LOAD = Math.min(40, NUMBER_OF_POKEMONS);
        loadedPosition = NUMBER_TO_LOAD;
        updateTiles();
        scrollPane.setVvalue(scrollPane.getVmin());
    }

    void addTiles() {
        while (tilePane.getChildren().size() < loadedPosition)
            tilePane.getChildren().add(new PokemonTileControl(pokemonList.getId(tilePane.getChildren().size()), onClickFunction));
    }

    void updateTiles() {
        tilePane.getChildren().clear();
        addTiles();
    }

    public void setClickedTileColor(String color) {
        this.color = color;
    }

    public PokemonType getChosen() {
        return chosen;
    }
}
