package sample.model.fetchers;

import sample.model.datamodels.PokemonType;

public class FetchTest {

    public static void main(String... args) {
        PokemonType poks = (PokemonType) new PokemonTypeFetcher().fetchAndParse("https://pokeapi.co/api/v2/pokemon/1");

        System.out.println("ID: " + poks.getId());
        System.out.println("Height: " + poks.getHeight());
        System.out.println("Weight: " + poks.getWeight());
        System.out.println("Name: " + poks.getName());
    }
}
