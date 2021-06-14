package sample.controllers.fightPrepControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import sample.components.SingleLogControl;
import sample.model.Constants;
import sample.model.datamodels.PokemonType;
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
    FlowPane flowPane;

    @FXML
    ImageView leftPokemonImageView;

    @FXML
    ImageView rightPokemonImageView;

    @FXML
    Pane resultsPane;

    @FXML
    Label resultLabel;

    @FXML
    Label scoreLabelLeft;

    @FXML
    Label scoreLabelRight;

    @FXML
    Label scoreLabelStalemate;

    public void init(GeneralLogs logs) {
        this.logs = logs;

        setLabels();

        for (int i = 0; i < logs.getAllFights(); i++)
            flowPane.getChildren().add(new SingleLogControl(logs.getLogs()[i]));
    }

    private void setLabels() {
        String winner = logs.getWinnerName();
        resultLabel.setText(winner.equals("NONE") ? "Neither Pokemon could get an advantage!" : winner + " came out on top!");

        scoreLabelLeft.setText(logs.getLeftWins().toString());
        scoreLabelRight.setText(logs.getRightWins().toString());
        scoreLabelStalemate.setText(String.valueOf(Constants.NUMBER_OF_FIGHTS - logs.getRightWins() - logs.getLeftWins()));
    }

    public void setImages(PokemonType leftPokemon, PokemonType rightPokemon) {
        leftPokemonImageView.setImage(new Image("file:resources/sprites/pokemon/" + leftPokemon.getId() + ".png"));
        rightPokemonImageView.setImage(new Image("file:resources/sprites/pokemon/" + rightPokemon.getId() + ".png"));
    }

}
