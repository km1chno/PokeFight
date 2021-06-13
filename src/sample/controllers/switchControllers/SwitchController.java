package sample.controllers.switchControllers;

import sample.model.exceptions.HttpException;

public interface SwitchController {
    public void switchTo() throws HttpException;
}
