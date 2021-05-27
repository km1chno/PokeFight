package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import sample.model.providers.PokemonTypeListFilter;

import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

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

    private void submit() {
        String name = filterBarNameInput.getText();
        String countString = filterBarCountInput.getText();

        if (countString.equals(""))
            countString = "2000";

        int count = Integer.parseInt(countString);
        libraryUpdateFunction.apply(count, new PokemonTypeListFilter(name));
    }

    public void setLibraryUpdateFunction(BiFunction<Integer, PokemonTypeListFilter, Void> f) {
        libraryUpdateFunction = f;
    }

    public void onFilterBarSubmitButtonClick(ActionEvent event) { submit(); }

    public void onTextFieldEnter(ActionEvent event) { submit(); }

    public void onFilterBarGoBackButtonClick(ActionEvent event) { SceneSwitchController.goHome(event); }
}
