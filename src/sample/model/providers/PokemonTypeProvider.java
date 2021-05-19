package sample.model.providers;

import sample.model.datamodels.PokemonType;
import sample.model.fetchers.PokemonTypeFetcher;

import java.util.HashMap;

public class PokemonTypeProvider {
    private static HashMap<Integer, PokemonType> pokemonTypeHashMap = new HashMap<>();
    private static PokemonTypeFetcher pokemonFetcher = new PokemonTypeFetcher();

    private static void fetchData(int id) {
        pokemonTypeHashMap.put(id, (PokemonType) pokemonFetcher.fetchAndParse(id));
    }

    public static PokemonType getData(int id) {
        if (!pokemonTypeHashMap.containsKey(id)) {
            fetchData(id);
        }
        return pokemonTypeHashMap.get(id);
    }
}
