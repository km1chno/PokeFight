package sample.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import sample.controllers.LibraryFilterBarController;

import java.util.Objects;

public class LibraryFilterBarControl extends AnchorPane {
    LibraryFilterBarController controller;

    public LibraryFilterBarControl() {
        super();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/libraryFilterBar.fxml"));
            this.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../css/filterBar.css")).toExternalForm());

            controller = new LibraryFilterBarController();

            loader.setController(controller);
            Node node = loader.load();
            this.getChildren().add(node);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LibraryFilterBarController getController() {
        return controller;
    }
}
