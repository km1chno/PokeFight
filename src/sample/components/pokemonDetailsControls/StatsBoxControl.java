package sample.components.pokemonDetailsControls;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import sample.controllers.pokemonDetailsControllers.StatsBoxController;

import java.util.Map;
import java.util.Objects;

public class StatsBoxControl extends AnchorPane {
    StatsBoxController controller;

    public StatsBoxControl() {
        super();

        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../view/pokemonDetailsViews/statsBox.fxml")));
            this.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../../css/pokemonDetails/statsBox.css")).toExternalForm());
            controller = new StatsBoxController();

            loader.setController(controller);
            Node node = loader.load();
            this.getChildren().add(node);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setIcons(Map<String, Double> stats) {
        controller.setStats(stats);
    }

    public void setBackgroundColor(String type) {
        controller.setBackgroundColor(type);
    }

    public StatsBoxController getController() {
        return controller;
    }
}
