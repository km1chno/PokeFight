package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.model.datamodels.PokemonType;

public class SinglePokemonDetailsController {
    private PokemonType pokemon;
    private SceneSwitchController sceneController;

    public SinglePokemonDetailsController() {
        sceneController = new SceneSwitchController();
    }

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

    @FXML
    Button pokemonDetailsGoBackButton;

    public void setPokemon(PokemonType pokemon) {
        this.pokemon = pokemon;

        pokemonDetailsNameLabel.setText(pokemon.getName());
        pokemonDetailsIdLabel.setText("ID: " + pokemon.getId());
        pokemonDetailsHeightLabel.setText("Height: " + pokemon.getHeight());
        pokemonDetailsWeightLabel.setText("Weight: " + pokemon.getWeight());
        pokemonDetailsImageView.setImage(new Image(pokemon.getSprites().getFrontDefault()));
    }

    public void onSinglePokemonDetailsGoBackClick(ActionEvent event) {
        try {
            sceneController.switchToView(event, "libraryView");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
