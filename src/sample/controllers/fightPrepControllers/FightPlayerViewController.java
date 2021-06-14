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
import sample.model.exceptions.MCTSException;
import sample.model.fight.SimulatedPokemon;
import sample.model.mcts.Game;
import sample.model.mcts.MCTS;

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
    @FXML
    Label logLabel;

    private enum PlayerType {
        HUMAN,
        COMPUTER
    }

    private final String[] statName = { "HP", "attack", "defense", "sp. attack", "sp. defense", "speed", "accuracy", "evasion"};

    private PokemonInstance leftPokemon, rightPokemon;
    private PlayerType leftPlayer, rightPlayer;
    private FightingPokemon nextToMove = FightingPokemon.LEFT;
    private SimulatedPokemon leftSimulatedPokemon, rightSimulatedPokemon;
    private Game mainGame;
    private MCTS engine;

    private PlayerType playerToMoveType() { return nextToMove == FightingPokemon.LEFT ? leftPlayer : rightPlayer; }

    public void setPokemon(PokemonInstance left, PokemonInstance right) {
        leftPokemon = left;
        rightPokemon = right;
        leftSimulatedPokemon = new SimulatedPokemon(left);
        rightSimulatedPokemon = new SimulatedPokemon(right);
        mainGame = new Game(leftSimulatedPokemon, rightSimulatedPokemon);
        engine = new MCTS();
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
                int finalI = i;
                button.setOnAction(actionEvent -> humanMove(finalI, move));
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
            tooltipBox.getChildren().add(new Label("Heals for " + move.getMeta().getHealing() + "% of maxHP"));
        }
        if (move.getMeta().getDrain() > 0) {
            tooltipBox.getChildren().add(new Label("Drains " + move.getMeta().getDrain() + "% of damage done"));
        }
        else if (move.getMeta().getDrain() < 0) {
            tooltipBox.getChildren().add(new Label(-move.getMeta().getDrain() + "% of damage done is taken as damage"));
        }
        if (move.getMeta().getAilment().getStatus() != Status.NONE) {
            tooltipBox.getChildren().add(new Label("Applies " + move.getMeta().getAilment().getStatus() + " with a " + move.getMeta().getAilmentChance() + "% base probability"));
        }
        for (Move.MoveMetaData.MoveStatChange statChange : move.getStatChanges()) {
            if (statChange.getChange() < 0) {
                tooltipBox.getChildren().add(new Label(
                        "Has a "
                        + move.getMeta().getStatChance()
                        + "% base chance to lower enemy's "
                        + statName[statChange.getIndex()]
                        + " by " + -statChange.getChange()
                ));
            }
            else {
                tooltipBox.getChildren().add(new Label(
                        "Has a "
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
        tooltipBox.getChildren().add(new Label("Using this move more than " + move.getPowerPoints() + " will render it useless"));

        tooltipBox.setAlignment(Pos.CENTER);
        for (Node label : tooltipBox.getChildren())
            ((Label) label).setWrapText(true);
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

        int d;
        if( nextToMove == FightingPokemon.LEFT) d=1;
        else d=0;

        try {
           mainGame = engine.getNextMove(mainGame, d);
        }catch (MCTSException e){
           e.printStackTrace();
        }
        nextToMove = nextToMove.opposite();

        updateLogLabel();

        getMove();
    }

    private void humanMove(int moveId, Move move) {
        disablePokemon(nextToMove);
        System.out.println(nextToMove + " used " + move.getName());

        int d;
        if( nextToMove == FightingPokemon.LEFT) d=0;
        else d=1;
        mainGame.move(d, moveId);
        nextToMove = nextToMove.opposite();

        updateLogLabel();
        getMove();
    }

    public void getMove() {
        updateHealthIndicators();
        if (playerToMoveType() == PlayerType.HUMAN)
            enablePokemon(nextToMove);
        else
            computerMove();
    }

    private void updateLogLabel() {
        logLabel.setText("");
        for(String s: mainGame.getLogs()) {
            logLabel.setText(logLabel.getText() + "\n" + s);
        }
        if(mainGame.getStatus()==Game.LEFT){
            logLabel.setText(logLabel.getText() + "\n" + leftSimulatedPokemon.getPokemonType().getName() + " wins!");
        }
        else if(mainGame.getStatus()==Game.RIGHT){
            logLabel.setText(logLabel.getText() + "\n" + rightSimulatedPokemon.getPokemonType().getName() + " wins!");
        }
        else if(mainGame.getStatus()==Game.DRAW){
            logLabel.setText(logLabel.getText() + "\n" + "draw!");
        }
    }

    private void updateHealthIndicators() {
        double leftHpFraction = (double)mainGame.left.getFinalHp()/mainGame.left.getHp();
        leftHealthIndicator.setWidth(leftHpFraction * leftHealthBar.getWidth());
        double rightHpFraction = (double)mainGame.right.getFinalHp()/mainGame.right.getHp();
        rightHealthIndicator.setWidth(rightHpFraction * rightHealthBar.getWidth());
    }

    private void setImages() {
        leftPokemonImageView.setImage(new Image("file:resources/sprites/pokemon/" + leftPokemon.getPokemonType().getId() + ".png"));
        rightPokemonImageView.setImage(new Image("file:resources/sprites/pokemon/" + rightPokemon.getPokemonType().getId() + ".png"));
    }
}
