package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.model.providers.PokemonTypeListFilter;

import java.io.IOException;
import java.util.function.BiFunction;

public class LibraryFilterBarController {
    private BiFunction<Integer, PokemonTypeListFilter, Void> libraryUpdateFunction;

    @FXML
    TextField filterBarNameInput;

    @FXML
    TextField filterBarCountInput;

    @FXML
    Button filterBarSubmitButton;

    @FXML
    Button filterBarGoBackButton;

    public void setLibraryUpdateFunction(BiFunction<Integer, PokemonTypeListFilter, Void> f) {
        libraryUpdateFunction = f;
    }

    public void onFilterBarSubmitButtonClick(ActionEvent event) {
        String name = filterBarNameInput.getText();
        String countString = filterBarCountInput.getText();

        if (countString.equals(""))
            countString = "2000";

        if (countString.length() > 1 && countString.charAt(0) == '0')
            return;
        for (int i = 0; i < countString.length(); i++) {
            if (countString.charAt(i) < '0' || countString.charAt(i) > '9')
                return;
        }
        int count = Integer.parseInt(countString);
        libraryUpdateFunction.apply(count, new PokemonTypeListFilter(name));
    }

    public void onFilterBarGoBackButtonClick(ActionEvent event) {
        try {
            new SceneSwitchController().switchToView(event, "welcomeView");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
