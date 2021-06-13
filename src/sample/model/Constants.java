package sample.model;

import sample.model.datamodels.Move;

public class Constants {
    public static final Move EMPTY_MOVE;

    public static final String API_BASE_URL;

    public static final int MAX_POKEDEX_POKEMON_NUMBER;

    public static final int TYPES_NUMBER;

    public static final int NUMBER_OF_FIGHTS;

    static {
        EMPTY_MOVE = new Move();

        API_BASE_URL = "https://pokeapi.co/api/v2/";

        MAX_POKEDEX_POKEMON_NUMBER = 898;

        TYPES_NUMBER = 18;

        NUMBER_OF_FIGHTS = 100;
    }
}
