package sample.controllers.fightPrepControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import sample.components.SingleLogControl;
import sample.model.fight.GeneralLogs;
import sample.model.fight.SingleFightLog;

public class SingleLogController {

    private SingleLogControl singleLogControl;

    @FXML
    FlowPane flowPane;

    @FXML
    public Label winnerLabel;

    @FXML
    public Button expandButton;

    public SingleLogController(SingleLogControl singleLogControl) { this.singleLogControl = singleLogControl; }

    public void onExpandButtonClick(ActionEvent event) {
        expandButton.setVisible(false);
        flowPane.getChildren().addAll(singleLogControl.getMoves());
    }
}
