package sample.model.fight;

import sample.model.datamodels.*;
import sample.model.exceptions.ToManyFightsException;

enum LogsPrecision{
    LOW,
    MEDIUM,
    HIGH
}
enum FightingPokemon{
    LEFT,
    RIGHT,
    NONE
}


public class Simulator {
    private final int defaultNumberOfFights = 100;
    private final LogsPrecision defaultLogsPrecision=LogsPrecision.MEDIUM;
    private final int fightsUpperBound = 10000000;
    private final int maxRoundsWithNoDamage = 100;

    private int numberOfFights;
    private LogsPrecision logsPrecision;

    public Simulator(){
        numberOfFights=defaultNumberOfFights;
        logsPrecision=defaultLogsPrecision;
    }

    public void set(int numberOfFights,  LogsPrecision logsPrecision) throws ToManyFightsException{
        if(numberOfFights>fightsUpperBound){
            throw new ToManyFightsException();
        }
        this.logsPrecision=logsPrecision;
        this.numberOfFights=numberOfFights;
    }

    public void set(int numberOfFights) throws ToManyFightsException{
        if(numberOfFights>fightsUpperBound){
            throw new ToManyFightsException();
        }
        this.numberOfFights=numberOfFights;
    }

    public void set(LogsPrecision logsPrecision){
        this.logsPrecision=logsPrecision;
    }


    public GeneralLogs simulate(PokemonInstance leftPokemon, PokemonInstance rightPokemon){

        GeneralLogs logs = new GeneralLogs(leftPokemon.getPokemonType(), rightPokemon.getPokemonType());

        for(int i=0; i<numberOfFights; i++){
             SingleFightLog log = singleFight(leftPokemon, rightPokemon);
             logs.addResult(log);
        }
        return logs;
    }

    public SingleFightLog singleFight(PokemonInstance leftPokemon, PokemonInstance rightPokemon){

        return null;
    }
}


