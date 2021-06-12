package sample.controllers;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import sample.controllers.fightPrepControllers.FightPrepViewController;
import sample.controllers.fightPrepControllers.FightResultViewController;
import sample.controllers.pokemonDetailsControllers.SinglePokemonDetailsController;
import sample.model.datamodels.PokemonType;
import sample.model.exceptions.HttpException;
import sample.model.fight.GeneralLogs;
import sample.model.providers.PokemonTypeListProvider;

import java.io.IOException;
import java.util.Objects;

public class SceneSwitchController {
    private static Stage primaryStage;
    private static Stage stage;
    private static Scene scene;
    private static Parent root;
    private static FXMLLoader loader;

    public static void setPrimaryStage(Stage stage) { primaryStage = stage; }

    static public Stage sourceOfEvent(Event event) {
        return (Stage)((Node)event.getSource()).getScene().getWindow();
    }

    // Sets the escape Stage
    public static void escapeToView(Stage window, String name) {
        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                try {
                    switchToView(window, name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Switches the current Scene displayed on window to the Scene defined by fxmlPath and cssPath
    public static void switchToView(Stage window, String fxmlPath, String cssPath) throws IOException {
        loader = new FXMLLoader(Objects.requireNonNull(SceneSwitchController.class.getResource(fxmlPath)));
        root = loader.load();
        stage = window;
        scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(SceneSwitchController.class.getResource(cssPath)).toExternalForm());

        stage.setScene(scene);
        stage.show();
    }

    // Switches the current Scene displayed on window to the Scene defined by src/view/{name}.fxml and src/css/{name}.css
    public static void switchToView(Stage window, String name) throws IOException {
        switchToView(window,"../view/" + name + ".fxml", "../css/" + name + ".css");
    }

    // Switches Scene based on event instead of stage
    public static void switchToView(Event event, String name) throws IOException {
        switchToView(sourceOfEvent(event), name);
    }

    public static void switchToSinglePokemonDetails(ActionEvent event, int id) throws IOException {
        loader = new FXMLLoader(Objects.requireNonNull(SceneSwitchController.class.getResource("../view/pokemonDetailsViews/singlePokemonDetailsView.fxml")));
        root = loader.load();
        SinglePokemonDetailsController pokemonDetailsController = loader.getController();
        pokemonDetailsController.setPokemon(id);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(SceneSwitchController.class.getResource("../css/pokemonDetails/singlePokemonDetailsView.css")).toExternalForm());

        stage.setScene(scene);
        stage.show();
    }

    public static void switchToLibraryView(Event event) throws IOException {
        Stage window = sourceOfEvent(event);
        switchToView(window, "loadingView");

        Task<Void> fetchPokemons = new Task<>() {
            @Override
            public Void call() throws HttpException {
                PokemonTypeListProvider.getData();
                return null;
            }
        };

        fetchPokemons.setOnSucceeded(workerStateEvent -> {
            try {
                switchToView(window, "libraryView");
                escapeToView(window, "welcomeView");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        fetchPokemons.setOnFailed(workerStateEvent -> handleException(fetchPokemons.getException()));

        Thread thread = new Thread(fetchPokemons);
        thread.setDaemon(true);
        thread.start();
    }

    public static void handleException(Throwable exception) {
        /* TO-DO: Create cases for different exceptions */
        while (exception.getCause() != null)
            exception = exception.getCause();

        try {
            switchToView(primaryStage, "fetchFailView");
        } catch (IOException e) {
            e.printStackTrace();
        }

        exception.printStackTrace();
    }

    public static void goHome(ActionEvent event) {
        try {
            SceneSwitchController.switchToView(event, "welcomeView");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void switchToFightPrepView(ActionEvent event, PokemonType left, PokemonType right) {
        loader = new FXMLLoader(Objects.requireNonNull(SceneSwitchController.class.getResource("../view/fightPrep/fightPrepView.fxml")));

        try {
            root = (Parent) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FightPrepViewController fightPrepController = loader.getController();
        fightPrepController.setPokemons(left, right);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(SceneSwitchController.class.getResource("../css/fightPrep/fightPrepView.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void switchToFightResultView(ActionEvent event, GeneralLogs logs) throws IOException {
        switchToView(event, "fightPrep/fightResultView");
        escapeToView(sourceOfEvent(event), "fightPrep/fightPrepView");
        FightResultViewController controller = loader.getController();
        controller.setLogs(logs);
    }

    public static Scene getScene() { return scene; }
}
