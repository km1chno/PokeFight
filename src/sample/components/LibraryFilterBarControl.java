package sample.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import sample.controllers.LibraryFilterBarController;

public class LibraryFilterBarControl extends AnchorPane {
    LibraryFilterBarController controller;

    public LibraryFilterBarControl() {
        super();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/libraryFilterBar.fxml"));
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
