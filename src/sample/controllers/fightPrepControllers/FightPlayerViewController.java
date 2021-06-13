package sample.controllers.fightPrepControllers;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import sample.model.Constants;
import sample.model.datamodels.FightingPokemon;
import sample.model.datamodels.Move;
import sample.model.datamodels.PokemonInstance;
import sample.model.datamodels.Status;

public class FightPlayerViewController {

    @FXML
    ImageView leftPokemonImageView;
    @FXML
    ImageView rightPokemonImageView;
    @FXML
    VBox leftButtonBox;
    @FXML
    VBox rightButtonBox;
    @FXML
    Rectangle leftHealthBar;
    @FXML
    Rectangle leftHealthIndicator;
    @FXML
    Rectangle rightHealthBar;
    @FXML
    Rectangle rightHealthIndicator;
    @FXML
    VBox tooltipBox;

    private enum PlayerType {
        HUMAN,
        COMPUTER
    }

    private final String[] statName = { "HP", "attack", "defense", "sp. attack", "sp. defense", "speed", "accuracy", "evasion"};

    private PokemonInstance leftPokemon, rightPokemon;
    private PlayerType leftPlayer, rightPlayer;
    private FightingPokemon nextToMove = FightingPokemon.LEFT;

    private PlayerType playerToMoveType() { return nextToMove == FightingPokemon.LEFT ? leftPlayer : rightPlayer; }

    public void setPokemon(PokemonInstance left, PokemonInstance right) {
        leftPokemon = left;
        rightPokemon = right;

        setImages();

        for (Node child : leftButtonBox.getChildren()) {
            VBox.setMargin(child, new Insets(10, 0, 10, 0));
        }

        for (Node child : rightButtonBox.getChildren()) {
            VBox.setMargin(child, new Insets(10, 0, 10, 0));
        }

        setButtons(FightingPokemon.LEFT);
        setButtons(FightingPokemon.RIGHT);
    }

    public void setPlayers(boolean left, boolean right) {
        leftPlayer = left ? PlayerType.HUMAN : PlayerType.COMPUTER;
        rightPlayer = right ? PlayerType.HUMAN : PlayerType.COMPUTER;

        disablePokemon(FightingPokemon.LEFT);
        disablePokemon(FightingPokemon.RIGHT);
    }

    private void setButtons(FightingPokemon side) {
        PokemonInstance pokemon = side == FightingPokemon.LEFT ? leftPokemon : rightPokemon;
        VBox buttonBox = side == FightingPokemon.LEFT ? leftButtonBox : rightButtonBox;
        for (int i = 0; i < 4; i++) {
            Button button = (Button) buttonBox.getChildren().get(i);
            Move move  = pokemon.getMoves()[i];

            button.setText(move.getName());
            if (move != Constants.EMPTY_MOVE) {
                button.setOnAction(actionEvent -> humanMove(move));
                button.setOnMouseEntered(mouseEvent -> updateTooltip(move));
            }
        }
    }

    private void updateTooltip(Move move) {
        tooltipBox.getChildren().clear();

        tooltipBox.getChildren().add(new Label("This is a " + move.getType() + " type move"));
        if (move.getPower() > 0) {
            tooltipBox.getChildren().add(new Label("Does " + move.getPower() + " base damage to enemy"));
        }
        if (move.getMeta().getMaxHits() > 1) {
            tooltipBox.getChildren().add(new Label("Hits between " + move.getMeta().getMinHits() + " and " + move.getMeta().getMaxHits() + " times"));
        }
        if (move.getMeta().getHealing() > 0) {
            tooltipBox.getChildren().add(new Label("Heals for " + move.getMeta().getHealing() + " HP"));
        }
        if (move.getMeta().getDrain() > 0) {
            tooltipBox.getChildren().add(new Label("Drains " + move.getMeta().getDrain() + "% of damage done"));
        }
        else if (move.getMeta().getDrain() < 0) {
            tooltipBox.getChildren().add(new Label(move.getMeta().getDrain() + "% of damage done is taken as damage"));
        }
        if (move.getMeta().getAilment().getStatus() != Status.NONE) {
            tooltipBox.getChildren().add(new Label("Applies " + move.getMeta().getAilment().getStatus() + " with a " + move.getMeta().getAilmentChance() + "% base probability"));
        }
        for (Move.MoveMetaData.MoveStatChange statChange : move.getStatChanges()) {
            if (statChange.getChange() < 0) {
                tooltipBox.getChildren().add(new Label("Has a "
                        + move.getMeta().getStatChance()
                        + "% base chance to lower enemy's "
                        + statName[statChange.getIndex()]
                        + " by " + -statChange.getChange()
                ));
            }
            else {
                tooltipBox.getChildren().add(new Label("Has a "
                        + move.getMeta().getStatChance()
                        + " % base chance to buff its "
                        + statName[statChange.getIndex()]
                        + " by " + statChange.getChange()
                ));
            }
        }
        if (move.getMeta().getMaxTurns() > 1) {
            tooltipBox.getChildren().add(new Label("This move's effects last between " + move.getMeta().getMinTurns() + " and " + move.getMeta().getMaxTurns() + " turns"));
        }

        tooltipBox.setAlignment(Pos.CENTER);
    }

    private void disablePokemon(FightingPokemon side) {
        VBox buttonBox = side == FightingPokemon.LEFT ? leftButtonBox : rightButtonBox;
        for (int i = 0; i < 4; i++)
            buttonBox.getChildren().get(i).setDisable(true);
    }

    private void enablePokemon(FightingPokemon side) {
        VBox buttonBox = side == FightingPokemon.LEFT ? leftButtonBox : rightButtonBox;
        PokemonInstance pokemon = side == FightingPokemon.LEFT ? leftPokemon : rightPokemon;
        for (int i = 0; i < 4; i++)
            if (pokemon.getMoves()[i] != Constants.EMPTY_MOVE)
                buttonBox.getChildren().get(i).setDisable(false);
    }

    private void computerMove() {
        System.out.println(nextToMove + " is choosing the singular best move");
        nextToMove = nextToMove.opposite();
        // TODO - actually calculate move
        getMove();
    }

    private void humanMove(Move move) {
        disablePokemon(nextToMove);
        System.out.println(nextToMove + " used " + move.getName());
        nextToMove = nextToMove.opposite();
        getMove();
    }

    public void getMove() {
        updateHealthIndicators();
        if (playerToMoveType() == PlayerType.HUMAN)
            enablePokemon(nextToMove);
        else
            computerMove();
    }

    private void updateHealthIndicators() {
        double leftHpFraction = 1; // TODO get actual currentHP/maxHP
        leftHealthIndicator.setWidth(leftHpFraction * leftHealthBar.getWidth());
        double rightHpFraction = 0.7;
        rightHealthIndicator.setWidth(rightHpFraction * rightHealthBar.getWidth());
    }

    private void setImages() {
        leftPokemonImageView.setImage(new Image("file:resources/sprites/pokemon/" + leftPokemon.getPokemonType().getId() + ".png"));
        rightPokemonImageView.setImage(new Image("file:resources/sprites/pokemon/" + rightPokemon.getPokemonType().getId() + ".png"));
    }
}
