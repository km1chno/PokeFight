package sample.model.fight;

import sample.model.datamodels.PokemonInstance;

public class SimulatedPokemon extends PokemonInstance {
    int currentHP;

    public SimulatedPokemon(PokemonInstance pokemon){
        super(pokemon);
        currentHP = pokemon.getHp();
    }



}
