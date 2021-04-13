package sample.controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Label;

public class SinglePokemonTypeTileController {
    @FXML
    private Label pokemonTileNameLabel;

    @FXML
    private Label pokemonTileIdLabel;

    @FXML
    Label pokemonTileHeightLabel;

    @FXML
    Label pokemonTypeWeightLabel;

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

    /*public void setPokemonTileNameLabel(String name) {
        pokemonTileNameLabel.setText(name);
    }

    public void setPokemonTileIdLabel(int id) {
        pokemonTileIdLabel.setText("ID: " + id);
    }

    public void setPokemonTileHeightLabel(int height) {
        pokemonTileHeightLabel.setText("Height: " + height);
    }

    public void setPokemonTypeWeightLabel(int weight) {
        pokemonTypeWeightLabel.setText("Width: " + weight);
    }*/
}
