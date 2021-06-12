package sample.model.providers;

import sample.model.datamodels.Result;

import java.util.Objects;

import static sample.model.Utils.levenshteinDistance;

public class PokemonTypeListFilter {
    public String nameSearch = "";

    public PokemonTypeListFilter(String nameSearch) {
        this.nameSearch = nameSearch.toLowerCase();
    }

    public boolean test(Result pokemon) {
        if (Objects.equals("", nameSearch)) return true;
        return levenshteinDistance(pokemon, nameSearch) <= Math.max(pokemon.name.length() - nameSearch.length() + 1, 3);
    }
}
