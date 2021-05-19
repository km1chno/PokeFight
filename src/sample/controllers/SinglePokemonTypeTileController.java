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
    private int id;
    private String name;
    private String url;

    private final SceneSwitchController switchController;

    public SinglePokemonTypeTileController() {
        switchController = new SceneSwitchController();
    }

    @FXML
    private Label pokemonTileNameLabel;

    @FXML
    private Label pokemonTileIdLabel;

    @FXML
    private ImageView pokemonTileImageView;

    @FXML
    private Button pokemonTileMoreButton;

    public void setPokemon(int id, String name, String url) {
        this.id = id;
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1);;
        this.url = url;

        pokemonTileNameLabel.setText(this.name);
        pokemonTileIdLabel.setText("ID: " + this.id);
        pokemonTileImageView.setImage(new Image("file:resources/sprites/pokemon/" + this.id + ".png"));
    }

    public void onMoreButtonClick(ActionEvent event) {
        try {
            switchController.switchToSinglePokemonDetails(event, id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
