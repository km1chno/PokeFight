package sample.controllers.switchControllers;

import java.io.IOException;

public class ExceptionHandler extends BasicSwitchController {
    public ExceptionHandler() throws IOException {
        super("FetchFailView");
    }
}
