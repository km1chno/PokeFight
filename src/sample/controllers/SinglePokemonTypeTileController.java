package sample.controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class SinglePokemonTypeTileController {
    @FXML
    private Label pokemonTileNameLabel;

    @FXML
    private Label pokemonTileIdLabel;

    @FXML
    private Label pokemonTileHeightLabel;

    @FXML
    private Label pokemonTypeWeightLabel;

    @FXML
    private ImageView pokemonTileImageView;

    public Label getPokemonTileNameLabel() {
        return pokemonTileNameLabel;
    }

    public Label getPokemonTileIdLabel() {
        return pokemonTileIdLabel;
    }

    public Label getPokemonTileHeightLabel() {
        return pokemonTileHeightLabel;
    }

    public Label getPokemonTypeWeightLabel() {
        return pokemonTypeWeightLabel;
    }

    public ImageView getPokemonTileImageView() { return pokemonTileImageView; }
}
