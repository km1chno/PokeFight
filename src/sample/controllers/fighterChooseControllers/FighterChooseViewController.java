package sample.controllers.fighterChooseControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.layout.AnchorPane;
import sample.components.fighterChooseControls.PokemonTypeScrollList;
import sample.controllers.SceneSwitchController;
import sample.controllers.fightPrepControllers.FightPrepViewController;
import sample.controllers.switchControllers.BasicSwitchController;
import sample.model.Utils;
import sample.model.datamodels.PokemonType;
import sample.model.exceptions.HttpException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FighterChooseViewController {
    private PokemonTypeScrollList leftScrollList;
    private PokemonTypeScrollList rightScrollList;

    @FXML
    AnchorPane anchorPane;

    @FXML
    Button chooseFighterAdvanceButton;

    @FXML
    Button goBackButton;

    @FXML
    public void initialize() throws HttpException {
        leftScrollList = new PokemonTypeScrollList("redBordered");
        rightScrollList = new PokemonTypeScrollList("blueBordered");

        leftScrollList.setLayoutX(20);
        leftScrollList.setLayoutY(100);

        rightScrollList.setLayoutX(650);
        rightScrollList.setLayoutY(100);

        anchorPane.getChildren().add(leftScrollList);
        anchorPane.getChildren().add(rightScrollList);
    }

    public void onChooseFighterAdvanceButtonClick(ActionEvent event) throws IOException {
        PokemonType chosenLeft = leftScrollList.getChosen();
        PokemonType chosenRight = rightScrollList.getChosen();

        if (chosenLeft == null || chosenRight == null) {
            Utils.showNoPokemonChosenDialog();
            return;
        }

        //SceneSwitchController.switchToFightPrepView(chosenLeft, chosenRight);
        new BasicSwitchController("fightPrep/fightPrepView") {
            @Override
            public void switchTo() {
                ((FightPrepViewController) loader.getController()).setPokemons(chosenLeft, chosenRight);
                super.switchTo();
            }
        }.switchTo();
    }

    public void onGoBackButtonClick(ActionEvent event) {
        SceneSwitchController.goHome();
    }
}
