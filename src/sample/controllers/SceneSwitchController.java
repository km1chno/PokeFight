package sample.controllers;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import sample.controllers.fightPrepControllers.FightPrepViewController;
import sample.controllers.fightPrepControllers.FightResultViewController;
import sample.controllers.pokemonDetailsControllers.SinglePokemonDetailsController;
import sample.controllers.switchControllers.BasicSwitchController;
import sample.controllers.switchControllers.ExceptionHandler;
import sample.controllers.switchControllers.LoadingSwitchController;
import sample.controllers.switchControllers.SwitchController;
import sample.model.datamodels.PokemonType;
import sample.model.exceptions.HttpException;
import sample.model.fight.GeneralLogs;
import sample.model.providers.PokemonTypeListProvider;

import java.io.IOException;
import java.util.Objects;

public class SceneSwitchController {

    // Sets the escape Stage
    /*public static void escapeToView(String name) {
        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                try {
                    switchToView(name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }*/

    public static void handleException(Throwable exception) {
        /* TO-DO: Create cases for different exceptions */
        while (exception.getCause() != null)
            exception = exception.getCause();

        try {
            ExceptionHandler handler = new ExceptionHandler();
            handler.switchTo();
        } catch (IOException | HttpException ignored) {}

        exception.printStackTrace();
    }

    public static void goHome() {
        try {
            new BasicSwitchController("welcomeView").switchTo();
        } catch (IOException | HttpException e) {
            e.printStackTrace();
        }
    }

}
