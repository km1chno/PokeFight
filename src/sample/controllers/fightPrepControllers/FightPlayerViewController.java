package sample.controllers.fightPrepControllers;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import sample.model.Constants;
import sample.model.datamodels.FightingPokemon;
import sample.model.datamodels.Move;
import sample.model.datamodels.PokemonInstance;

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
    Rectangle leftHealthIndicator;

    @FXML
    Rectangle rightHealthIndicator;

    private enum PlayerType {
        HUMAN,
        COMPUTER
    }

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

    private void humanMove(Move move) {
        disablePokemon(nextToMove);
        System.out.println(nextToMove + " used " + move.getName());
        nextToMove = nextToMove.opposite();
        getMove();
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
            if (move != Constants.EMPTY_MOVE)
                button.setOnAction(actionEvent -> humanMove(move));
        }
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

    public void getMove() {
        if (playerToMoveType() == PlayerType.HUMAN)
            enablePokemon(nextToMove);
        else
            computerMove();
    }

    private void setImages() {
        leftPokemonImageView.setImage(new Image("file:resources/sprites/pokemon/" + leftPokemon.getPokemonType().getId() + ".png"));
        rightPokemonImageView.setImage(new Image("file:resources/sprites/pokemon/" + rightPokemon.getPokemonType().getId() + ".png"));
    }
}
