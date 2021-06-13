package sample.controllers.switchControllers;

import javafx.concurrent.Task;
import sample.model.exceptions.HttpException;
import sample.model.providers.PokemonTypeListProvider;

import java.io.IOException;

public class LoadingSwitchController extends BasicSwitchController {
    private final BasicSwitchController loadingSwitch;

    public LoadingSwitchController(String fxmlPath, String cssPath) throws IOException {
        super(fxmlPath, cssPath);
        loadingSwitch = new BasicSwitchController("loadingView");
    }

    public LoadingSwitchController(String name) throws IOException {
        super(name);
        loadingSwitch = new BasicSwitchController("loadingView");
    }

    @Override
    public void switchTo() {
        loadingSwitch.switchTo();
        System.out.println("LOADING VIEW LOADED");

        Task<Void> fetchPokemons = new Task<>() {
            @Override
            public Void call() throws HttpException {
                PokemonTypeListProvider.getData();
                return null;
            }
        };

        fetchPokemons.setOnSucceeded(workerStateEvent -> {
            super.switchTo();
            System.out.println("POKEMONS");
        });

        Thread thread = new Thread(fetchPokemons);
        thread.setDaemon(true);
        thread.start();
    }
}
