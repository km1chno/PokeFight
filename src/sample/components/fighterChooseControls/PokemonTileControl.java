package sample.components.fighterChooseControls;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import sample.controllers.fighterChooseControllers.PokemonTileController;

import java.util.Objects;
import java.util.function.Consumer;

public class PokemonTileControl extends AnchorPane {
    PokemonTileController controller;
    private int id;

    public PokemonTileControl(int id, Consumer<Integer> chosenFunction) {
        super();

        this.id = id;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/fighterChooseViews/pokemonTile.fxml"));
            this.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../../css/fighterChoose/pokemonTile.css")).toExternalForm());

            controller = new PokemonTileController();

            loader.setController(controller);
            Node node = loader.load();
            this.getChildren().add(node);

            controller.setPokemon(id);
            controller.setChosenFunction(chosenFunction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePaneStyle(String style) {
        controller.deletePaneStyle(style);
    }

    public void addPaneStyle(String style) {
        controller.addPaneStyle(style);
    }
}
