package sample.components.fighterChooseControls;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import sample.controllers.fighterChooseControllers.PokemonTypeScrollListController;
import sample.model.datamodels.PokemonType;
import sample.model.exceptions.HttpException;

import java.io.IOException;
import java.util.Objects;

public class PokemonTypeScrollList extends AnchorPane {
    PokemonTypeScrollListController controller;

    public PokemonTypeScrollList(String color) throws HttpException {
        super();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/fighterChooseViews/pokemonTypeScrollListView.fxml"));
            this.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../../css/fighterChoose/pokemonTypeScrollList.css")).toExternalForm());

            controller = new PokemonTypeScrollListController();

            loader.setController(controller);
            Node node = loader.load();
            this.getChildren().add(node);

            controller.setClickedTileColor(color);
        } catch (IOException e) {
            Throwable ex = e;
            while (ex.getCause() != null)
                ex = ex.getCause();
            throw (HttpException) ex;
        }
    }

    public PokemonType getChosen() {
        return controller.getChosen();
    }
}
