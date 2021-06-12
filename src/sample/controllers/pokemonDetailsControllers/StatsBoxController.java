package sample.controllers.pokemonDetailsControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import sample.model.datamodels.Type;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class StatsBoxController {
    private final double MAX_STAT = 255.0;
    private final List<String> allStats = Arrays.asList("hp", "attack", "defense", "special-attack", "special-defense", "speed");

    @FXML
    Rectangle hpRect;

    @FXML
    Rectangle aRect;

    @FXML
    Rectangle dRect;

    @FXML
    Rectangle saRect;

    @FXML
    Rectangle sdRect;

    @FXML
    Rectangle spRect;

    @FXML
    Label hpLabel;

    @FXML
    Label aLabel;

    @FXML
    Label dLabel;

    @FXML
    Label saLabel;

    @FXML
    Label sdLabel;

    @FXML
    Label spLabel;

    @FXML
    Pane mainContainer;

    public void setStats(Map<String, Double> stats) {
        List<Rectangle> rectangles = Arrays.asList(hpRect, aRect, dRect, saRect, sdRect, spRect);
        List<Label> labels = Arrays.asList(hpLabel, aLabel, dLabel, saLabel, sdLabel, spLabel);

        for (int i = 0; i < allStats.size(); i++) {
            String stat = allStats.get(i);
            Rectangle rect = rectangles.get(i);
            Label label = labels.get(i);
            int base_stat = (int) Math.round(stats.get(stat));

            double oldH = rect.getHeight();
            rect.setHeight(stats.get(stat)/MAX_STAT * oldH);
            double dy = oldH - rect.getHeight();
            rect.setY(rect.getY() + dy);
            label.setLayoutY(label.getLayoutY() + dy);
            label.setText(String.valueOf(base_stat));
        }
    }

    public void setBackgroundColor(Type type) {
        mainContainer.getStyleClass().add(type.getName());
    }
}
