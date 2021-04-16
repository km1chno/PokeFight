package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.model.datamodels.PokemonType;

import java.io.IOException;
import java.util.Objects;

public class SinglePokemonTypeTileController {
    private PokemonType pokemon;
    private final SceneSwitchController switchController;

    public SinglePokemonTypeTileController() {
        switchController = new SceneSwitchController();
    }

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

    @FXML
    private Button pokemonTileMoreButton;

    public void setPokemon(PokemonType pokemon) {
        this.pokemon = pokemon;

        pokemonTileNameLabel.setText(pokemon.getName());
        pokemonTileIdLabel.setText("ID: " + pokemon.getId());
        pokemonTileHeightLabel.setText("Height: " + pokemon.getHeight());
        pokemonTypeWeightLabel.setText("Weight: " + pokemon.getWeight());
        pokemonTileImageView.setImage(new Image("file:resources/sprites/pokemon/" + pokemon.getId() + ".png"));
    }

    public void onMoreButtonClick(ActionEvent event) {
        try {
            switchController.switchToSinglePokemonDetails(event, pokemon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
