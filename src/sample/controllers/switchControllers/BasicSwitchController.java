package sample.controllers.switchControllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import sample.Main;
import sample.controllers.SceneSwitchController;
import sample.model.Constants;
import sample.model.exceptions.HttpException;

import java.io.IOException;
import java.util.Objects;

public class BasicSwitchController extends AbstractSwitchController {
    private final SwitchController prevSwitch;

    public BasicSwitchController(String fxmlPath, String cssPath) throws IOException {
        prevSwitch = SceneSwitchController.getCurrentSwitch();
        loader = new FXMLLoader(Objects.requireNonNull(SceneSwitchController.class.getResource(fxmlPath)));
        root = loader.load();
        scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(SceneSwitchController.class.getResource(cssPath)).toExternalForm());
    }

    public BasicSwitchController(String name) throws IOException {
        this("../view/" + name + ".fxml", "../css/" + name + ".css");
    }

    @Override
    public void switchTo() throws HttpException {
        if (prevSwitch != null) {
            scene.setOnKeyPressed(keyEvent -> {
                if (keyEvent.getCode() == KeyCode.ESCAPE) {
                    try {
                        prevSwitch.switchTo();
                    } catch (HttpException e) {
                        SceneSwitchController.handleException(e);
                    }
                }
            });
        }

        SceneSwitchController.setCurrentSwitch(this);
        Constants.stage.setScene(scene);
        Constants.stage.show();
    }
}
