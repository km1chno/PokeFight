package sample.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import sample.controllers.SinglePokemonTypeTileController;
import sample.model.datamodels.PokemonType;

public class SinglePokemonTypeTileControl extends AnchorPane {
    SinglePokemonTypeTileController controller;

    public SinglePokemonTypeTileControl(PokemonType pokemon) {
        super();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/singlePokemonTypeTile.fxml"));

            controller = new SinglePokemonTypeTileController();

            loader.setController(controller);
            Node node = loader.load();

            this.getChildren().add(node);

            controller.getPokemonTileNameLabel().setText(pokemon.getName());
            controller.getPokemonTileIdLabel().setText("ID: " + pokemon.getId());
            controller.getPokemonTileHeightLabel().setText("Height: " + pokemon.getHeight());
            controller.getPokemonTypeWeightLabel().setText("Weight: " + pokemon.getWeight());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
