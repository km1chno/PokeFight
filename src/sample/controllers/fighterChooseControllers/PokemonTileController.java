package sample.controllers.fighterChooseControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.function.Consumer;

public class PokemonTileController {
    private int id;
    private Consumer<Integer> chosenFunction;

    @FXML
    private ImageView pokemonTileImageView;

    @FXML
    private AnchorPane pokemonTilePane;

    @FXML
    private Button pokemonTileButton;

    @FXML
    private Pane pokemonTileBorderPane;

    public void setPokemon(int id) {
        this.id = id;
        pokemonTileImageView.setImage(new Image("file:resources/sprites/pokemon/" + this.id + ".png"));
    }

    public void setChosenFunction(Consumer<Integer> chosenFunction) {
        this.chosenFunction = chosenFunction;
    }

    public void addPaneStyle(String style) {
        pokemonTileBorderPane.getStyleClass().add(style);
    }

    public void deletePaneStyle(String style) {
        pokemonTileBorderPane.getStyleClass().removeAll(style);
    }

    public void onPokemonTileButtonClick(ActionEvent event) {
        chosenFunction.accept(id);
    }
}
