package sample.components.pokemonDetailsControls;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import sample.controllers.pokemonDetailsControllers.MainInfoBoxController;
import sample.model.datamodels.PokemonType;

import java.util.Objects;

public class MainInfoBoxControl extends AnchorPane {
    MainInfoBoxController controller;

    public MainInfoBoxControl() {
        super();

        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../view/pokemonDetailsViews/mainInfoBoxView.fxml")));
            this.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../../css/pokemonDetails/mainInfoBox.css")).toExternalForm());
            controller = new MainInfoBoxController();

            loader.setController(controller);
            Node node = loader.load();
            this.getChildren().add(node);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPokemon(PokemonType pokemon) {
        controller.setPokemon(pokemon);
    }

    public void setBackgroundColor(String type) {
        controller.setBackgroundColor(type);
    }

    public MainInfoBoxController getController() {
        return controller;
    }
}
