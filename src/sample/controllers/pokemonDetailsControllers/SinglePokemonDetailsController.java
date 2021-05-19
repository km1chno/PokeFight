package sample.controllers.pokemonDetailsControllers;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.components.pokemonDetailsControls.MainInfoBoxControl;
import sample.components.pokemonDetailsControls.StatsBoxControl;
import sample.components.pokemonDetailsControls.TypesBoxControl;
import sample.controllers.SceneSwitchController;
import sample.model.datamodels.PokemonType;
import sample.model.fetchers.PokemonTypeFetcher;
import sample.model.providers.PokemonTypeProvider;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.ResourceBundle;

public class SinglePokemonDetailsController implements Initializable {
    private PokemonType pokemon;
    private String url;

    private final MainInfoBoxControl mainInfoBoxControl = new MainInfoBoxControl();
    private final TypesBoxControl typesBoxControl = new TypesBoxControl();
    private final StatsBoxControl statsBox = new StatsBoxControl();

    private final SceneSwitchController sceneController = new SceneSwitchController();

    public SinglePokemonDetailsController() {
    }

    @FXML
    Button singlePokemonDetailsGoBackButton;

    @FXML
    Pane anchorPane;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainInfoBoxControl.relocate(20, 20);
        anchorPane.getChildren().add(mainInfoBoxControl);
        anchorPane.getChildren().get(anchorPane.getChildren().size()-1).toFront();

        typesBoxControl.relocate(590, 20);
        anchorPane.getChildren().add(typesBoxControl);
        anchorPane.getChildren().get(anchorPane.getChildren().size()-1).toFront();

        statsBox.relocate(590, 410);
        anchorPane.getChildren().add(statsBox);
        anchorPane.getChildren().get(anchorPane.getChildren().size()-1).toFront();
    }

    public void updateInfo() {
        mainInfoBoxControl.setPokemon(pokemon);
        typesBoxControl.setIcons(pokemon.getTypes());
        statsBox.setIcons(pokemon.getStats());

        String type = pokemon.getTypes()[0].name;
        mainInfoBoxControl.setBackgroundColor(type);
        statsBox.setBackgroundColor(type);
        typesBoxControl.setBackgroundColor(type);
    }

    public void setPokemon(int id) {
        Task<Void> fetchPokemon = new Task<>() {
            @Override
            public Void call() {
                pokemon = PokemonTypeProvider.getData(id);
                return null;
            }
        };
        fetchPokemon.setOnSucceeded(workerStateEvent -> updateInfo());

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
