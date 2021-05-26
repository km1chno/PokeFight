package sample.model.providers;

import sample.model.datamodels.PokemonTypeList;
import sample.model.exceptions.HttpException;
import sample.model.fetchers.PokemonTypeListFetcher;

public class PokemonTypeListProvider {
    private static PokemonTypeList pokemonList = null;
    private static boolean upToDate = false;
    private static String url = "https://pokeapi.co/api/v2/pokemon?limit=200";

    private static void fetchData() throws HttpException {
        pokemonList = (PokemonTypeList) new PokemonTypeListFetcher().fetchAndParse(url);
        upToDate = true;
    }

    public static PokemonTypeList getData() throws HttpException {
        if (!upToDate) {
            PokemonTypeListProvider.fetchData();
        }
        return pokemonList;
    }

    public static PokemonTypeList getDataWithFilter (int count, PokemonTypeListFilter filter) throws HttpException {
        if (!upToDate) {
            PokemonTypeListProvider.fetchData();
        }
        return pokemonList.filter(count, filter);
    }
}
