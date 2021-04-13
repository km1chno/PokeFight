package sample.model.datamodels;

import com.google.gson.Gson;

public class GsonTest {
    public static void main(String... args) {
        Gson gson = new Gson();

        String json_string = "{\"id\":1, \"height\": 7, \"weight\": 69, \"name\": \"bulbasaur\"}";
        PokemonType poks = gson.fromJson(json_string, PokemonType.class);

        System.out.println("ID: " + poks.getId());
        System.out.println("Height: " + poks.getHeight());
        System.out.println("Weight: " + poks.getWeight());
        System.out.println("Name: " + poks.getName());
    }
}
