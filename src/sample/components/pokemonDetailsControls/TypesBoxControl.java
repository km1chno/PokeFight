package sample.components.pokemonDetailsControls;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import sample.controllers.pokemonDetailsControllers.MainInfoBoxController;
import sample.controllers.pokemonDetailsControllers.TypesBoxController;
import sample.model.datamodels.PokemonType;
import sample.model.datamodels.Result;
import sample.model.datamodels.Type;

import java.util.Objects;

public class TypesBoxControl extends AnchorPane {
    TypesBoxController controller;

    public TypesBoxControl() {
        super();

        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../view/pokemonDetailsViews/typesBox.fxml")));
            this.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../../css/pokemonDetails/typesBox.css")).toExternalForm());
            controller = new TypesBoxController();

            loader.setController(controller);
            Node node = loader.load();
            this.getChildren().add(node);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setIcons(Type[] types) {
        controller.setIcons(types);
    }

    public void setBackgroundColor(Type type) {
        controller.setBackgroundColor(type);
    }

    public TypesBoxController getController() {
        return controller;
    }
}
