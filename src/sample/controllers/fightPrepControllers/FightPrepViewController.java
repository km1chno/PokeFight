package sample.controllers.fightPrepControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FightPrepViewController implements Initializable {
    private PokemonType leftPokemon;
    private PokemonType rightPokemon;
    private Simulator fightSimulator;

    private ArrayList<TextField> leftIVFields;
    private ArrayList<TextField> rightIVFields;
    private ArrayList<ComboBox<String>> leftMovesFields;
    private ArrayList<ComboBox<String>> rightMovesFields;

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

    @FXML
    Label vsLabel;

    @FXML
    Pane resultsPane;

    @FXML
    Label winnerNameLabel;

    @FXML
    Label scoreLabelLeft;

    @FXML
    Label scoreLabelStalemate;

    @FXML
    Label scoreLabelRight;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fightSimulator = new Simulator();

        leftIVFields = new ArrayList<>(Arrays.asList(leftIVhp, leftIVAttack, leftIVdef, leftIVspAttack, leftIVspDef, leftIVspeed));
        rightIVFields = new ArrayList<>(Arrays.asList(rightIVhp, rightIVAttack, rightIVdef, rightIVspAttack, rightIVspDef, rightIVspeed));

        leftMovesFields = new ArrayList<>(Arrays.asList(leftMove1, leftMove2, leftMove3, leftMove4));
        rightMovesFields = new ArrayList<>(Arrays.asList(rightMove1, rightMove2, rightMove3, rightMove4));
    }

    public void setPokemons(PokemonType leftPokemon, PokemonType rightPokemon) {
        Utils.configureNumericTextField(leftLvl);
        Utils.configureNumericTextField(leftExp);
        for (TextField field : leftIVFields) {
            Utils.configureNumericTextField(field);
        }

        Utils.configureNumericTextField(rightLvl);
        Utils.configureNumericTextField(rightExp);
        for (TextField field : rightIVFields) {
            Utils.configureNumericTextField(field);
        }

        this.leftPokemon = leftPokemon;
        this.rightPokemon = rightPokemon;

        leftPokemonImageView.setImage(new Image("file:resources/sprites/pokemon/" + this.leftPokemon.getId() + ".png"));
        rightPokemonImageView.setImage(new Image("file:resources/sprites/pokemon/" + this.rightPokemon.getId() + ".png"));

        for (MoveResult move : leftPokemon.getMoves()) {
            for (ComboBox<String> comboBox : leftMovesFields) {
                comboBox.getItems().add(move.move.name);
            }
        }

        for (MoveResult move : rightPokemon.getMoves()) {
            for (ComboBox<String> comboBox : rightMovesFields) {
                comboBox.getItems().add(move.move.name);
            }
        }
    }

    public void showResult(GeneralLogs logs) {
        vsLabel.setStyle("-fx-opacity: 0");
        resultsPane.setStyle("-fx-opacity: 1");
        winnerNameLabel.setText(logs.getWinnerName() + " wins!");
        scoreLabelLeft.setText(String.valueOf(logs.getLeftWins()));
        scoreLabelStalemate.setText(String.valueOf(logs.getStalemates()));
        scoreLabelRight.setText(String.valueOf(logs.getRightWins()));
    }

    public void onFightButtonClick(ActionEvent event) {
        Move[] leftPokemonMoves = new Move[4];
        Move[] rightPokemonMoves = new Move[4];

        try {
            for (int i = 0; i < 4; i++) {
                leftPokemonMoves[i] = (leftMovesFields.get(i).getValue() == null) ? Constants.EMPTY_MOVE : MoveProvider.getData(leftMovesFields.get(i).getValue());
                rightPokemonMoves[i] = (rightMovesFields.get(i).getValue() == null) ? Constants.EMPTY_MOVE : MoveProvider.getData(rightMovesFields.get(i).getValue());
            }
        } catch (HttpException e) {
            SceneSwitchController.handleException(e);
        }

        ArrayList<Integer> leftIV;
        ArrayList<Integer> rightIV;
        PokemonInstance leftPokemonInstance;
        PokemonInstance rightPokemonInstance;

        try {
            leftIV = leftIVFields.stream().map(x -> Integer.parseInt(x.getText())).collect(Collectors.toCollection(ArrayList::new));
            rightIV = rightIVFields.stream().map(x -> Integer.parseInt(x.getText())).collect(Collectors.toCollection(ArrayList::new));

            leftPokemonInstance = new PokemonInstance(leftPokemon, leftPokemonMoves, Integer.parseInt(leftLvl.getText()), Integer.parseInt(leftExp.getText()), leftIV);
            rightPokemonInstance = new PokemonInstance(rightPokemon, rightPokemonMoves, Integer.parseInt(rightLvl.getText()), Integer.parseInt(rightExp.getText()), rightIV);
        } catch (NumberFormatException | IncorrectStatsException e) {
            Utils.showIncorrectStatsDialog();
            return;
        }

        System.out.println("They are ready to fight!");
        leftPokemonInstance.print();
        rightPokemonInstance.print();

        GeneralLogs fightLogs = fightSimulator.simulate(leftPokemonInstance, rightPokemonInstance);

        showResult(fightLogs);
    }

    public void onGoBackButtonClick(ActionEvent event) {
        try {
            SceneSwitchController.switchToView(SceneSwitchController.sourceOfEvent(event), "../view/fighterChooseViews/fighterChooseView.fxml", "../css/fighterChoose/fighterChooseView.css");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onRandomStatsButtonClick(ActionEvent event) {
        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            leftMovesFields.get(i).getSelectionModel().select(Math.abs(random.nextInt()) % leftMovesFields.get(i).getItems().size());
            rightMovesFields.get(i).getSelectionModel().select(Math.abs(random.nextInt()) % rightMovesFields.get(i).getItems().size());

        }
        for (int i = 0; i < 6; i++) {
            leftIVFields.get(i).setText(String.valueOf(Math.abs(random.nextInt() % 5)));
            rightIVFields.get(i).setText(String.valueOf(Math.abs(random.nextInt() % 5)));
        }

        leftLvl.setText(String.valueOf(Math.abs(random.nextInt()) % 101));
        leftExp.setText(String.valueOf(Math.abs(random.nextInt()) % 101));
        rightLvl.setText(String.valueOf(Math.abs(random.nextInt()) % 101));
        rightExp.setText(String.valueOf(Math.abs(random.nextInt()) % 101));
    }
}
