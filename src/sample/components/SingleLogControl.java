package sample.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import sample.controllers.SinglePokemonTypeTileController;
import sample.controllers.fightPrepControllers.SingleLogController;
import sample.model.fight.GeneralLogs;
import sample.model.fight.SingleFightLog;

import java.util.ArrayList;
import java.util.Objects;

public class SingleLogControl extends Pane {
    private SingleFightLog log;
    private ArrayList<Label> moves;
    private SingleLogController controller;

    public SingleLogControl(SingleFightLog log) {
        super();

        this.log = log;
        moves = new ArrayList<>();
        for (String labelText : log.getMoveLogs()) {
            moves.add(new Label(labelText));
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/fightPrep/singleLogView.fxml"));
            this.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../css/fightPrep/singleLogView.css")).toExternalForm());

            controller = new SingleLogController(this);
            loader.setController(controller);

            Node node = loader.load();
            this.getChildren().add(node);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Label> getMoves() { return moves; }
}
