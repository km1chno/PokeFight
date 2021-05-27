package sample.model;

import sample.model.datamodels.Move;

public class Constants {
    public static final Move EMPTY_MOVE;

    public static String API_BASE_URL;

    public static int MAX_POKEDEX_POKEMON_NUMBER;

    public static int TYPES_NUMBER;

    static {
        EMPTY_MOVE = new Move();

        API_BASE_URL = "https://pokeapi.co/api/v2/";

        MAX_POKEDEX_POKEMON_NUMBER = 898;

        TYPES_NUMBER = 18;
    }
}
