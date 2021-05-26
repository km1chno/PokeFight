package sample.model.providers;

import sample.model.datamodels.Result;

import java.util.ArrayList;
import java.util.Objects;

public class PokemonTypeListFilter {
    public String nameSearch = "";

    public PokemonTypeListFilter(String nameSearch) {
        this.nameSearch = nameSearch.toLowerCase();
    }

    private int levenshteinDistance(Result pokemon) {
        int n = nameSearch.length(), m = pokemon.name.length();
        if (n > 100) return Integer.MAX_VALUE;
        if (n == 0) return m;
        if (m == 0) return n;

        Integer[][] partial = new Integer[n + 1][m + 1];
        for (int i = 0; i <= n; i++)
            partial[i][0] = i;
        for (int i = 1; i <= m; i++)
            partial[0][i] = i;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int sameChar = nameSearch.charAt(i - 1) == pokemon.name.charAt(j - 1) ? 0 : 1;
                partial[i][j] = Integer.min(Integer.min(partial[i - 1][j], partial[i][j - 1]) + 1, partial[i - 1][j - 1] + sameChar);
            }
        }
        System.out.println(pokemon.name + "'s distance is " + partial[n][m]);
        return partial[n][m];
    }

    public boolean test(Result pokemon) {
        /*if (nameSearch.length() > pokemon.name.length())
            return false;
        for (int i = 0; i < nameSearch.length(); i++) {
            if (Character.toLowerCase(nameSearch.charAt(i)) != Character.toLowerCase(pokemon.name.charAt(i)))
                return false;
        }
        return true;*/
        if (Objects.equals("", nameSearch)) return true;
        return 2 * levenshteinDistance(pokemon) <= pokemon.name.length();
    }
}
