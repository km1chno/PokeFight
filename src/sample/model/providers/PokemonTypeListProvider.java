package sample.model.providers;

import sample.model.datamodels.PokemonTypeList;
import sample.model.fetchers.PokemonTypeListFetcher;

public class PokemonTypeListProvider {
    private static PokemonTypeList pokemonList = null;
    private static boolean upToDate = false;
    private static String url = "https://pokeapi.co/api/v2/pokemon?limit=898";

    private static void fetchData() {
        pokemonList = (PokemonTypeList) new PokemonTypeListFetcher().fetchAndParse(url);
        upToDate = true;
    }

    public static PokemonTypeList getData() {
        if (!upToDate) {
            PokemonTypeListProvider.fetchData();
        }
        return pokemonList;
    }

    public static PokemonTypeList getDataWithFilter(int count, PokemonTypeListFilter filter) {
        if (!upToDate) {
            PokemonTypeListProvider.fetchData();
        }
        return pokemonList.filter(count, filter);
    }
}
