package sample.controllers.fightPrepControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import sample.components.SingleLogControl;
import sample.model.fight.GeneralLogs;

public class FightResultViewController {
    private GeneralLogs logs;

    @FXML
    AnchorPane outerAnchor;

    @FXML
    ScrollPane scrollPane;

    @FXML
    AnchorPane anchorPane;

    @FXML
    ImageView leftPokemonImageView;

    @FXML
    ImageView rightPokemonImageView;

    @FXML
    Pane resultsPane;

    @FXML
    Label scoreLabelLeft;

    @FXML
    Label scoreLabelRight;

    @FXML
    Label scoreLabelStalemate;

    public void setLogs(GeneralLogs logs) {
        this.logs = logs;
        for (int i = 0; i < logs.getAllFights(); i++)
            anchorPane.getChildren().add(new SingleLogControl(logs.getLogs()[i]));
    }

}
