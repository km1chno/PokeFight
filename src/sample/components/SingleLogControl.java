package sample.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import sample.controllers.SinglePokemonTypeTileController;
import sample.controllers.fightPrepControllers.SingleLogController;
import sample.model.datamodels.FightingPokemon;
import sample.model.datamodels.PokemonType;
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
            Label moveLog = new Label(labelText);
            moveLog.setPrefWidth(1280.0);
            moves.add(moveLog);
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/fightPrep/singleLogView.fxml"));
            this.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../css/fightPrep/singleLogView.css")).toExternalForm());

            controller = new SingleLogController(this);
            loader.setController(controller);

            Node node = loader.load();
            this.getChildren().add(node);

            setLabels();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setLabels() {
        FightingPokemon winner = log.getWinner();
        if (winner == FightingPokemon.NONE) {
            controller.winnerLabel.setText("This fight is a draw!");
        }
        else {
            controller.winnerLabel.setText(log.getWinnerName().getName() + " " + "won this fight!");
            controller.winnerLabel.setStyle("-fx-text-fill: " + (winner == FightingPokemon.LEFT ? "pink" : "skyblue"));
        }
    }

    public ArrayList<Label> getMoves() { return moves; }
}
