package sample.model.datamodels;

import java.util.ArrayList;

public class SingleFightLog {
    private final PokemonType leftType;
    private final PokemonType rightType;
    FightingPokemon winner;
    Integer maxDamage;
    ArrayList<String> logs = new ArrayList<>();
    public SingleFightLog(PokemonType lType, PokemonType rType){
        leftType=lType;
        rightType=rType;
        winner=FightingPokemon.NONE;
        maxDamage=0;
    }
    public void addLog(FightingPokemon p, Move move, Integer damage){
        if(p==FightingPokemon.LEFT){
            logs.add(leftType.getName()+ " zadal "+ damage.toString() + " uzywajac " + move.getName() );
        }
        else if(p==FightingPokemon.RIGHT){
            logs.add(rightType.getName()+ " zadal "+ damage.toString() + " uzywajac " + move.getName() );
        }
        maxDamage=Integer.max(maxDamage, damage);
    }
    public void addLog(FightingPokemon p){
        if(p==FightingPokemon.LEFT){
            logs.add(leftType.getName() + " wygrywa walke!");
            winner=FightingPokemon.LEFT;
        }
        else if(p==FightingPokemon.RIGHT){
            logs.add(rightType.getName() + " wygrywa walke!");
            winner=FightingPokemon.RIGHT;
        }
        else{
            logs.add("Pat w walkach pokemonow? co do diabla?!");
            winner=FightingPokemon.NONE;
        }
    }

}
