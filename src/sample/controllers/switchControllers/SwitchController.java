package sample.controllers.switchControllers;

import sample.model.exceptions.HttpException;

public interface SwitchController {
    void switchTo() throws HttpException;
}
