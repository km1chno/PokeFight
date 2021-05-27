package sample.model.providers;

import sample.model.Constants;
import sample.model.datamodels.PokemonTypeList;
import sample.model.exceptions.HttpException;
import sample.model.fetchers.PokemonTypeListFetcher;

public class PokemonTypeListProvider {
    private static PokemonTypeList pokemonList = null;
    private static boolean upToDate = false;

    private static void fetchData() throws HttpException {
        String url = Constants.API_BASE_URL + "pokemon?limit=" + Constants.MAX_POKEDEX_POKEMON_NUMBER;
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
