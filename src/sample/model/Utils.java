package sample.model;

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
}
