package sample.controllers.fighterChooseControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.TilePane;
import sample.components.fighterChooseControls.PokemonTileControl;
import sample.controllers.SceneSwitchController;
import sample.model.datamodels.PokemonType;
import sample.model.datamodels.PokemonTypeList;
import sample.model.providers.PokemonTypeListProvider;
import sample.model.providers.PokemonTypeProvider;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class FighterChooseViewController implements Initializable {
    private PokemonTypeList pokemonList;
    private int NUMBER_OF_POKEMONS;

    private PokemonType chosenLeft;
    private PokemonType chosenRight;

    private Consumer<Integer> setLeft;
    private Consumer<Integer> setRight;

    private SceneSwitchController sceneSwitchController = new SceneSwitchController();

    @FXML
    ScrollPane leftScrollPane;

    @FXML
    ScrollPane rightScrollPane;

    @FXML
    TilePane leftTilePane;

    @FXML
    TilePane rightTilePane;

    @FXML
    Button chooseFighterAdvanceButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chosenLeft = null;
        chosenRight = null;

        setLeft = id -> {
            if (chosenLeft != null) {
                ((PokemonTileControl) leftTilePane.getChildren().get(chosenLeft.getId()-1)).deletePaneStyle("redBordered");
            }
            ((PokemonTileControl) leftTilePane.getChildren().get(id-1)).addPaneStyle("redBordered");
            chosenLeft = PokemonTypeProvider.getData(id);
        };

        setRight = id -> {
            if (chosenRight != null) {
                ((PokemonTileControl) rightTilePane.getChildren().get(chosenRight.getId()-1)).deletePaneStyle("blueBordered");
            }
            ((PokemonTileControl) rightTilePane.getChildren().get(id-1)).addPaneStyle("blueBordered");
            chosenRight = PokemonTypeProvider.getData(id);
        };

        leftScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        rightScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        pokemonList = PokemonTypeListProvider.getData();
        updateChooseView();
    }

    public void updateChooseView() {
        leftTilePane.getChildren().clear();
        rightTilePane.getChildren().clear();

        NUMBER_OF_POKEMONS = pokemonList.getCount();

        for (int i = 0; i < NUMBER_OF_POKEMONS; i++) {
            leftTilePane.getChildren().add(new PokemonTileControl(pokemonList.getId(i), setLeft));
            rightTilePane.getChildren().add(new PokemonTileControl(pokemonList.getId(i), setRight));
        }
    }

    public void onChooseFighterAdvanceButtonClick(ActionEvent event) {
        if (chosenLeft == null || chosenRight == null)
            return;
        sceneSwitchController.switchToFightPrepView(event, chosenLeft, chosenRight);
    }
}
