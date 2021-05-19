package sample.controllers.fightPrepControllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.model.datamodels.PokemonType;

public class FightPrepViewController {
    private PokemonType leftPokemon;
    private PokemonType rightPokemon;

    @FXML
    ImageView leftPokemonImageView;

    @FXML
    ImageView rightPokemonImageView;

    public void setPokemons(PokemonType leftPokemon, PokemonType rightPokemon) {
        this.leftPokemon = leftPokemon;
        this.rightPokemon = rightPokemon;

        leftPokemonImageView.setImage(new Image("file:resources/sprites/pokemon/" + this.leftPokemon.getId() + ".png"));
        rightPokemonImageView.setImage(new Image("file:resources/sprites/pokemon/" + this.rightPokemon.getId() + ".png"));
    }
}
