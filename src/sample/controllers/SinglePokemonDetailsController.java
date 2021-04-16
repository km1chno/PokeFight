package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.model.datamodels.PokemonType;

public class SinglePokemonDetailsController {
    private PokemonType pokemon;

    @FXML
    Label pokemonDetailsNameLabel;

    @FXML
    Label pokemonDetailsIdLabel;

    @FXML
    Label pokemonDetailsHeightLabel;

    @FXML
    Label pokemonDetailsWeightLabel;

    @FXML
    ImageView pokemonDetailsImageView;

    public void setPokemon(PokemonType pokemon) {
        this.pokemon = pokemon;

        pokemonDetailsNameLabel.setText(pokemon.getName());
        pokemonDetailsIdLabel.setText("ID: " + pokemon.getId());
        pokemonDetailsHeightLabel.setText("Height: " + pokemon.getHeight());
        pokemonDetailsWeightLabel.setText("Weight: " + pokemon.getWeight());
        pokemonDetailsImageView.setImage(new Image(pokemon.getSprites().getFrontDefault()));
    }
}
