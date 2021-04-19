package sample.model.providers;

import sample.model.datamodels.Result;

public class PokemonTypeListFilter {
    public String nameSearch = "";

    public PokemonTypeListFilter(String nameSearch) {
        this.nameSearch = nameSearch;
    }

    public boolean test(Result pokemon) {
        if (nameSearch.length() > pokemon.name.length())
            return false;
        for (int i = 0; i < nameSearch.length(); i++) {
            if (nameSearch.charAt(i) != pokemon.name.charAt(i))
                return false;
        }
        return true;
    }
}
