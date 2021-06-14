package sample.model.fight;

import sample.model.datamodels.FightingPokemon;
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
    public void addLog(ArrayList<String> logs){
        if(logs != null)
            this.logs.addAll(logs);
    }
    public void addLog(Game game){
        if(game.getStatus() == Game.LEFT){
            logs.add(leftType.getName() + " wins!");
            winner= FightingPokemon.LEFT;
        }
        else if(game.getStatus() == Game.RIGHT){
            logs.add(rightType.getName() + " wins!!");
            winner= FightingPokemon.RIGHT;
        }
        else{
            logs.add("Battle ends with a draw!");
            winner= FightingPokemon.NONE;
        }
    }
    public void print(){
        for(String s: logs){
            System.out.println(s);
        }
    }

    public FightingPokemon getWinner() {
        return winner;
    }

    public PokemonType getWinnerName() {
        if (winner == FightingPokemon.LEFT)
            return leftType;
        if (winner == FightingPokemon.RIGHT)
            return rightType;
        return null;
    }

    public ArrayList<String> getMoveLogs() { return logs; }

}
