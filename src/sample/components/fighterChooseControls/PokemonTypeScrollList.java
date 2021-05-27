package sample.components.fighterChooseControls;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import sample.controllers.fighterChooseControllers.PokemonTypeScrollListController;
import sample.model.datamodels.PokemonType;

import java.util.Objects;

public class PokemonTypeScrollList extends AnchorPane {
    PokemonTypeScrollListController controller;

    public PokemonTypeScrollList(String color) {
        super();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/fighterChooseViews/pokemonTypeScrollListView.fxml"));
            this.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../../css/fighterChoose/pokemonTypeScrollList.css")).toExternalForm());

            controller = new PokemonTypeScrollListController();

            loader.setController(controller);
            Node node = loader.load();
            this.getChildren().add(node);

            controller.setClickedTileColor(color);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PokemonType getChosen() {
        return controller.getChosen();
    }
}
