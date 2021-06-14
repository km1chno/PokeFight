package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.controllers.pokemonDetailsControllers.SinglePokemonDetailsController;
import sample.controllers.switchControllers.BasicSwitchController;
import sample.controllers.switchControllers.SwitchController;
import sample.model.exceptions.HttpException;

import java.io.IOException;

public class SinglePokemonTypeTileController {
    private int id;
    private String name;
    private String url;

    public SinglePokemonTypeTileController() {}

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
            new BasicSwitchController("../view/pokemonDetailsViews/singlePokemonDetailsView.fxml", "../css/pokemonDetails/singlePokemonDetailsView.css") {
                @Override
                public void switchTo() throws HttpException {
                    SinglePokemonDetailsController pokemonDetailsController = loader.getController();
                    pokemonDetailsController.setPokemon(id);
                    super.switchTo();
                }
            }.switchTo();
        } catch (IOException | HttpException e) {
            SceneSwitchController.handleException(e);
        }
    }
}
