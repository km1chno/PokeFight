package sample.controllers.fightPrepControllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import sample.model.Constants;
import sample.model.datamodels.FightingPokemon;
import sample.model.datamodels.PokemonInstance;
import sample.model.datamodels.PokemonType;

public class FightPlayerViewController {

    @FXML
    ImageView leftPokemonImageView;

    @FXML
    ImageView rightPokemonImageView;

    @FXML
    VBox leftButtonBox;

    @FXML
    VBox rightButtonBox;

    private enum PlayerType {
        HUMAN,
        COMPUTER
    }

    private PokemonInstance leftPokemon, rightPokemon;
    private PlayerType leftPlayer, rightPlayer;
    private FightingPokemon nextToMove = FightingPokemon.LEFT;

    public void setPokemon(PokemonInstance left, PokemonInstance right) {
        leftPokemon = left;
        rightPokemon = right;

        setImages();
        setMoves();
    }

    public void setPlayers(boolean left, boolean right) {
        leftPlayer = left ? PlayerType.HUMAN : PlayerType.COMPUTER;
        rightPlayer = right ? PlayerType.HUMAN : PlayerType.COMPUTER;

        disablePokemon(FightingPokemon.LEFT);
        disablePokemon(FightingPokemon.RIGHT);
    }

    private void setMoves() {
        for (int i = 0; i < 4; i++)
            ((Button) leftButtonBox.getChildren().get(i)).setText(leftPokemon.getMoves()[i].getName());
        for (int i = 0; i < 4; i++)
            ((Button) rightButtonBox.getChildren().get(i)).setText(rightPokemon.getMoves()[i].getName());
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

    private void setImages() {
        leftPokemonImageView.setImage(new Image("file:resources/sprites/pokemon/" + leftPokemon.getPokemonType().getId() + ".png"));
        rightPokemonImageView.setImage(new Image("file:resources/sprites/pokemon/" + rightPokemon.getPokemonType().getId() + ".png"));
    }
}
