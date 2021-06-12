package sample.model;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import sample.model.datamodels.Result;

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

    public static int levenshteinDistance(Result pokemon, String query) {
        int n = query.length(), m = pokemon.name.length();
        if (n > 100) return Integer.MAX_VALUE;
        if (n == 0) return m;
        if (m == 0) return n;

        Integer[][] partial = new Integer[n + 1][m + 1];
        for (int i = 0; i <= n; i++)
            partial[i][0] = i;
        for (int i = 1; i <= m; i++)
            partial[0][i] = i;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int sameChar = query.charAt(i - 1) == pokemon.name.charAt(j - 1) ? 0 : 1;
                partial[i][j] = Integer.min(Integer.min(partial[i - 1][j], partial[i][j - 1]) + 1, partial[i - 1][j - 1] + sameChar);
            }
        }
        return partial[n][m];
    }
}
