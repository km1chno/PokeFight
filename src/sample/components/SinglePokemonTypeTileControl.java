package sample.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import sample.controllers.SinglePokemonTypeTileController;
import sample.model.datamodels.PokemonType;

import java.util.Objects;

public class SinglePokemonTypeTileControl extends AnchorPane {
    SinglePokemonTypeTileController controller;
    private int id;
    private String name;
    private String url;

    public SinglePokemonTypeTileControl(int id, String name, String url) {
        super();

        this.id = id;
        this.name = name;
        this.url = url;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/singlePokemonTypeTile.fxml"));
            this.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../css/singlePokemonTypeTile.css")).toExternalForm());

            controller = new SinglePokemonTypeTileController();

            loader.setController(controller);
            Node node = loader.load();
            this.getChildren().add(node);

            controller.setPokemon(id, name, url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
