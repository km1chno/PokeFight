package sample.controllers.fightPrepControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.components.SingleLogControl;
import sample.model.fight.GeneralLogs;
import sample.model.fight.SingleFightLog;

public class SingleLogController {

    private SingleLogControl singleLogControl;

    @FXML
    AnchorPane anchorPane;

    @FXML
    Label winnerLabel;

    @FXML
    Label leftCounter;

    @FXML
    Label rightCounter;

    @FXML
    Label drawCounter;

    @FXML
    Button expandButton;

    public SingleLogController(SingleLogControl singleLogControl) { this.singleLogControl = singleLogControl; }

    public void onExpandButtonClick(ActionEvent event) {
        expandButton.setVisible(false);
        anchorPane.getChildren().addAll(singleLogControl.getMoves());
    }
}
