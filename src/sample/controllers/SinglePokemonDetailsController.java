package sample.controllers;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.model.datamodels.PokemonType;
import sample.model.fetchers.PokemonTypeFetcher;

public class SinglePokemonDetailsController {
    private PokemonType pokemon;
    private SceneSwitchController sceneController;
    private String url;

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

    public void updateInformations() {
        pokemonDetailsNameLabel.setText(pokemon.getName());
        pokemonDetailsIdLabel.setText("ID: " + pokemon.getId());
        pokemonDetailsHeightLabel.setText("Height: " + pokemon.getHeight());
        pokemonDetailsWeightLabel.setText("Weight: " + pokemon.getWeight());
        pokemonDetailsImageView.setImage(new Image("file:resources/sprites/pokemon/" + pokemon.getId() + ".png"));
    }

    public void setPokemon(String url) {
        this.url = url;

        Task<Void> fetchPokemon = new Task<>() {
            @Override
            public Void call() {
                pokemon = (PokemonType) new PokemonTypeFetcher().fetchAndParse(url);
                return null;
            }
        };
        fetchPokemon.setOnSucceeded(workerStateEvent -> updateInformations());

        Thread thread = new Thread(fetchPokemon);
        thread.setDaemon(true);
        thread.start();
    }

    public void onSinglePokemonDetailsGoBackClick(ActionEvent event) {
        try {
            sceneController.switchToLibraryView(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
