package sample.controllers.fightPrepControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.model.Utils;
import sample.model.datamodels.Move;
import sample.model.datamodels.MoveResult;
import sample.model.datamodels.PokemonInstance;
import sample.model.datamodels.PokemonType;
import sample.model.exceptions.IncorrectStatsException;
import sample.model.providers.MoveProvider;

import java.util.ArrayList;
import java.util.Arrays;

public class FightPrepViewController {
    private PokemonType leftPokemon;
    private PokemonType rightPokemon;

    @FXML
    ImageView leftPokemonImageView;

    @FXML
    ImageView rightPokemonImageView;

    @FXML
    ComboBox<String> leftMove1;

    @FXML
    ComboBox<String> leftMove2;

    @FXML
    ComboBox<String> leftMove3;

    @FXML
    ComboBox<String> leftMove4;

    @FXML
    TextField leftLvl;

    @FXML
    TextField leftExp;

    @FXML
    TextField leftIVhp;

    @FXML
    TextField leftIVAttack;

    @FXML
    TextField leftIVdef;

    @FXML
    TextField leftIVspAttack;

    @FXML
    TextField leftIVspDef;

    @FXML
    TextField leftIVspeed;

    @FXML
    Button fightButton;

    public void setPokemons(PokemonType leftPokemon, PokemonType rightPokemon) {
        Utils.configureNumericTextField(leftLvl);
        Utils.configureNumericTextField(leftExp);
        Utils.configureNumericTextField(leftIVhp);
        Utils.configureNumericTextField(leftIVAttack);
        Utils.configureNumericTextField(leftIVdef);
        Utils.configureNumericTextField(leftIVspAttack);
        Utils.configureNumericTextField(leftIVspDef);
        Utils.configureNumericTextField(leftIVspeed);

        this.leftPokemon = leftPokemon;
        this.rightPokemon = rightPokemon;

        leftPokemonImageView.setImage(new Image("file:resources/sprites/pokemon/" + this.leftPokemon.getId() + ".png"));
        rightPokemonImageView.setImage(new Image("file:resources/sprites/pokemon/" + this.rightPokemon.getId() + ".png"));

        for (MoveResult move : leftPokemon.getMoves()) {
            leftMove1.getItems().add(move.move.name);
            leftMove2.getItems().add(move.move.name);
            leftMove3.getItems().add(move.move.name);
            leftMove4.getItems().add(move.move.name);
        }
    }

    public void onFightButtonClick(ActionEvent event) {
        Move[] leftPokemonMoves = new Move[4];
        leftPokemonMoves[0] = MoveProvider.getData(leftMove1.getValue());
        leftPokemonMoves[1] = MoveProvider.getData(leftMove2.getValue());
        leftPokemonMoves[2] = MoveProvider.getData(leftMove3.getValue());
        leftPokemonMoves[3] = MoveProvider.getData(leftMove4.getValue());

        ArrayList<Integer> IV;

        try {
            IV = new ArrayList<Integer>(Arrays.asList(
                    Integer.parseInt(leftIVhp.getText()),
                    Integer.parseInt(leftIVAttack.getText()),
                    Integer.parseInt(leftIVdef.getText()),
                    Integer.parseInt(leftIVspAttack.getText()),
                    Integer.parseInt(leftIVspDef.getText()),
                    Integer.parseInt(leftIVspeed.getText())
            ));
        } catch (NumberFormatException e) {
            System.out.println("IncorrectStatsException");
            return;
        }


        PokemonInstance leftPokemonInstance;
        try {
            leftPokemonInstance = new PokemonInstance(leftPokemon, leftPokemonMoves, Integer.parseInt(leftLvl.getText()), Integer.parseInt(leftExp.getText()), IV);
        } catch (IncorrectStatsException e) {
            System.out.println("IncorrectStatsException");
            return;
        }

        System.out.println("Successfully created leftPokemon!");
        leftPokemonInstance.print();
    }
}
