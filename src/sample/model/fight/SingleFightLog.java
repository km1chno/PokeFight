package sample.model.fight;

import sample.model.datamodels.Move;
import sample.model.datamodels.PokemonType;
import sample.model.mcts.Game;

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
        winner= FightingPokemon.NONE;
        maxDamage=0;
    }
//    public void addLog(SimulatedPokemon p, Move move, Integer damage){
//        if(p== FightingPokemon.LEFT.LEFT){
//            logs.add(leftType.getName()+ " zadal "+ damage.toString() + " uzywajac " + move.getName() );
//        }
//        else if(p== FightingPokemon.LEFT.RIGHT){
//            logs.add(rightType.getName()+ " zadal "+ damage.toString() + " uzywajac " + move.getName() );
//        }
//        maxDamage=Integer.max(maxDamage, damage);
//    }
    public void addLog(Game game){
        if(game.getStatus() == Game.LEFT){
            logs.add(leftType.getName() + " wygrywa walke!");
            winner= FightingPokemon.LEFT;
        }
        else if(game.getStatus() == Game.RIGHT){
            logs.add(rightType.getName() + " wygrywa walke!");
            winner= FightingPokemon.RIGHT;
        }
        else{
            logs.add("Pat w walkach pokemonow? co do diabla?!");
            winner= FightingPokemon.NONE;
        }
    }

}
