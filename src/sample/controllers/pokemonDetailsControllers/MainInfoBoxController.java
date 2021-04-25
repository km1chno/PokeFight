package sample.controllers.pokemonDetailsControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import sample.model.datamodels.PokemonType;

public class MainInfoBoxController {
    @FXML
    private Pane imageWrapper;

    @FXML
    private Label nameLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Label heightLabel;

    @FXML
    private Label weightLabel;

    @FXML
    private ImageView spriteImageView;

    @FXML
    private Pane mainContainer;

    public void setPokemon(PokemonType pokemon) {
        nameLabel.setText(pokemon.getName());
        idLabel.setText("ID: " + pokemon.getId());
        heightLabel.setText("Height: " + pokemon.getHeight());
        weightLabel.setText("Weight: " + pokemon.getWeight());

        spriteImageView.setImage(new Image("file:resources/sprites/pokemon/" + pokemon.getId() + ".png"));
    }

    public void setBackgroundColor(String type) {
        mainContainer.getStyleClass().add(type);
    }
}
