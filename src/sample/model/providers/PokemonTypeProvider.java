package sample.model.providers;

import sample.model.datamodels.PokemonType;
import sample.model.exceptions.HttpException;
import sample.model.fetchers.PokemonTypeFetcher;

import java.util.HashMap;

public class PokemonTypeProvider {
    private static final HashMap<Integer, PokemonType> pokemonTypeHashMap = new HashMap<>();
    private static final PokemonTypeFetcher pokemonFetcher = new PokemonTypeFetcher();

    private static void fetchData(int id) throws HttpException {
        pokemonTypeHashMap.put(id, (PokemonType) pokemonFetcher.fetchAndParseFromId(id));
    }

    public static PokemonType getData(int id) throws HttpException {
        if (!pokemonTypeHashMap.containsKey(id)) {
            fetchData(id);
        }
        return pokemonTypeHashMap.get(id);
    }
}
