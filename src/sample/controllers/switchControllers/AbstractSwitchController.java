package sample.controllers.switchControllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import sample.model.exceptions.HttpException;

public abstract class AbstractSwitchController implements SwitchController {
    protected Scene scene;
    protected FXMLLoader loader;
    protected Parent root;

    @Override
    public abstract void switchTo() throws HttpException;

    /*@Override
    public abstract void returnTo() throws HttpException
     */
}
