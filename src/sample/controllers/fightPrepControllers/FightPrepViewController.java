package sample.controllers.fightPrepControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.controllers.SceneSwitchController;
import sample.model.Constants;
import sample.model.Utils;
import sample.model.datamodels.Move;
import sample.model.datamodels.MoveResult;
import sample.model.datamodels.PokemonInstance;
import sample.model.datamodels.PokemonType;
import sample.model.exceptions.HttpException;
import sample.model.exceptions.IncorrectStatsException;
import sample.model.fight.GeneralLogs;
import sample.model.fight.Simulator;
import sample.model.providers.MoveProvider;

import java.util.ArrayList;
import java.util.Arrays;

public class FightPrepViewController {
    private PokemonType leftPokemon;
    private PokemonType rightPokemon;
    private Simulator fightSimulator;

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
    ComboBox<String> rightMove1;

    @FXML
    ComboBox<String> rightMove2;

    @FXML
    ComboBox<String> rightMove3;

    @FXML
    ComboBox<String> rightMove4;

    @FXML
    TextField rightLvl;

    @FXML
    TextField rightExp;

    @FXML
    TextField rightIVhp;

    @FXML
    TextField rightIVAttack;

    @FXML
    TextField rightIVdef;

    @FXML
    TextField rightIVspAttack;

    @FXML
    TextField rightIVspDef;

    @FXML
    TextField rightIVspeed;

    @FXML
    Button fightButton;

    public void setPokemons(PokemonType leftPokemon, PokemonType rightPokemon) {
        fightSimulator = new Simulator();

        Utils.configureNumericTextField(leftLvl);
        Utils.configureNumericTextField(leftExp);
        Utils.configureNumericTextField(leftIVhp);
        Utils.configureNumericTextField(leftIVAttack);
        Utils.configureNumericTextField(leftIVdef);
        Utils.configureNumericTextField(leftIVspAttack);
        Utils.configureNumericTextField(leftIVspDef);
        Utils.configureNumericTextField(leftIVspeed);

        Utils.configureNumericTextField(rightLvl);
        Utils.configureNumericTextField(rightExp);
        Utils.configureNumericTextField(rightIVhp);
        Utils.configureNumericTextField(rightIVAttack);
        Utils.configureNumericTextField(rightIVdef);
        Utils.configureNumericTextField(rightIVspAttack);
        Utils.configureNumericTextField(rightIVspDef);
        Utils.configureNumericTextField(rightIVspeed);

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

        for (MoveResult move : rightPokemon.getMoves()) {
            rightMove1.getItems().add(move.move.name);
            rightMove2.getItems().add(move.move.name);
            rightMove3.getItems().add(move.move.name);
            rightMove4.getItems().add(move.move.name);
        }
    }

    public void onFightButtonClick(ActionEvent event) {
        Move[] leftPokemonMoves = new Move[4];
        Move[] rightPokemonMoves = new Move[4];
        try {
            leftPokemonMoves[0] = (leftMove1.getValue() == null) ? Constants.EMPTY_MOVE : MoveProvider.getData(leftMove1.getValue());
            leftPokemonMoves[1] = (leftMove2.getValue() == null) ? Constants.EMPTY_MOVE : MoveProvider.getData(leftMove2.getValue());
            leftPokemonMoves[2] = (leftMove3.getValue() == null) ? Constants.EMPTY_MOVE : MoveProvider.getData(leftMove3.getValue());
            leftPokemonMoves[3] = (leftMove4.getValue() == null) ? Constants.EMPTY_MOVE : MoveProvider.getData(leftMove4.getValue());

            rightPokemonMoves[0] = (rightMove1.getValue() == null) ? Constants.EMPTY_MOVE : MoveProvider.getData(rightMove1.getValue());
            rightPokemonMoves[1] = (rightMove2.getValue() == null) ? Constants.EMPTY_MOVE : MoveProvider.getData(rightMove2.getValue());
            rightPokemonMoves[2] = (rightMove3.getValue() == null) ? Constants.EMPTY_MOVE : MoveProvider.getData(rightMove3.getValue());
            rightPokemonMoves[3] = (rightMove4.getValue() == null) ? Constants.EMPTY_MOVE : MoveProvider.getData(rightMove4.getValue());
        } catch (HttpException e) {
            SceneSwitchController.handleException(e);
        }

        ArrayList<Integer> leftIV;
        ArrayList<Integer> rightIV;

        try {
            leftIV = new ArrayList<Integer>(Arrays.asList(
                    Integer.parseInt(leftIVhp.getText()),
                    Integer.parseInt(leftIVAttack.getText()),
                    Integer.parseInt(leftIVdef.getText()),
                    Integer.parseInt(leftIVspAttack.getText()),
                    Integer.parseInt(leftIVspDef.getText()),
                    Integer.parseInt(leftIVspeed.getText())
            ));

            rightIV = new ArrayList<Integer>(Arrays.asList(
                    Integer.parseInt(rightIVhp.getText()),
                    Integer.parseInt(rightIVAttack.getText()),
                    Integer.parseInt(rightIVdef.getText()),
                    Integer.parseInt(rightIVspAttack.getText()),
                    Integer.parseInt(rightIVspDef.getText()),
                    Integer.parseInt(rightIVspeed.getText())
            ));
        } catch (NumberFormatException e) {
            System.out.println("IncorrectStatsException");
            return;
        }

        PokemonInstance leftPokemonInstance;
        PokemonInstance rightPokemonInstance;

        try {
            leftPokemonInstance = new PokemonInstance(leftPokemon, leftPokemonMoves, Integer.parseInt(leftLvl.getText()), Integer.parseInt(leftExp.getText()), leftIV);
            rightPokemonInstance = new PokemonInstance(rightPokemon, rightPokemonMoves, Integer.parseInt(rightLvl.getText()), Integer.parseInt(rightExp.getText()), rightIV);
        } catch (IncorrectStatsException e) {
            System.out.println("IncorrectStatsException");
            return;
        }

        System.out.println("They are ready to fight!");
        leftPokemonInstance.print();
        rightPokemonInstance.print();

        GeneralLogs fightLogs = fightSimulator.simulate(leftPokemonInstance, rightPokemonInstance);

        fightLogs.print();
    }
}
