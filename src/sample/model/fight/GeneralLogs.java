package sample.model.fight;

import sample.model.datamodels.FightingPokemon;
import sample.model.datamodels.PokemonType;

import java.util.ArrayList;

public class GeneralLogs {
    private final PokemonType leftType;
    private final PokemonType rightType;

    ArrayList<SingleFightLog> logs;

    Integer allFights;
    Integer leftWins;
    Integer rightWins;
    Integer stalemates;
    Integer maxDamage;

    public GeneralLogs(PokemonType lType, PokemonType rType){
        leftType=lType;
        rightType=rType;
        allFights=leftWins=rightWins=stalemates=maxDamage=0;
        logs = new ArrayList<>();
    }

    public void addResult(SingleFightLog log){
        if(log.winner== FightingPokemon.LEFT){
            leftWins++;
        }
        else if(log.winner== FightingPokemon.RIGHT){
            rightWins++;
        }
        else{
            stalemates++;
        }
        allFights++;
        maxDamage = Integer.max(maxDamage, log.maxDamage);
        logs.add(log);
    }

    public SingleFightLog[] getLogs(){
        SingleFightLog[] res = new SingleFightLog[logs.size()];
        for(int i=0; i< logs.size(); i++){
            res[i]= logs.get(i);
        }
        return res;
    }

    public String getWinnerName(){
        if (leftWins > rightWins) return leftType.getName();
        else if (rightWins > leftWins) return rightType.getName();
        else return FightingPokemon.NONE.toString();
    }

    public Integer getAllFights() {
        return allFights;
    }

    public Integer getLeftWins() {
        return leftWins;
    }

    public Integer getRightWins() {
        return rightWins;
    }

    public Integer getStalemates() {
        return stalemates;
    }
}
