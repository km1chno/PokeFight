package sample.model;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

public class Utils {
    public static boolean isInteger(String s) {
        if (s.length() == 0)
            return false;
        if (s.length() > 1 && s.charAt(0) == '0')
            return false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) > '9' || s.charAt(i) < '0') {
                return false;
            }
        }
        return true;
    }

    public static void configureNumericTextField(TextField textField) {
        UnaryOperator<TextFormatter.Change> filter = change -> change.getControlNewText().matches("\\d*") ? change : null;
        TextFormatter<TextFormatter.Change> formatter = new TextFormatter<>(filter);

        textField.setTextFormatter(formatter);
    }

    public static void showIncorrectStatsDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Wrong stats input");
        alert.setHeaderText("Make sure you choose all the stats correctly (or use random stats button)");
        alert.setContentText("Pokemon Level must be an integer in range [1, 100]\nPokemon Exp must be a non-negative integer\nAll of Individual Values must be integers in range [0, 31]");

        alert.showAndWait();
    }

    public static void showNoPokemonChosenDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("You have to choose both pokemons");
        alert.setHeaderText("It looks like you have not chosen both fighters");

        alert.showAndWait();
    }
}
