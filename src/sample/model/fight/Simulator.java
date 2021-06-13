package sample.model.fight;

import sample.model.datamodels.*;
import sample.model.exceptions.MCTSException;
import sample.model.exceptions.ToManyFightsException;
import sample.model.mcts.Game;
import sample.model.mcts.MCTS;

enum LogsPrecision{
    LOW,
    MEDIUM,
    HIGH
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
            System.out.println(i+1 + " walka: " );
             SingleFightLog log = singleFight(leftPokemon, rightPokemon);
             logs.addResult(log);
        }
        return logs;
    }

    public SingleFightLog singleFight(PokemonInstance leftPokemon, PokemonInstance rightPokemon){
        SimulatedPokemon left = new SimulatedPokemon(leftPokemon);
        SimulatedPokemon right = new SimulatedPokemon(rightPokemon);
        int pokemonNum;
        if(left.getSpeed()> right.getSpeed()) {
            pokemonNum = 0;
        }
        else {
            pokemonNum = 1;
        }
        Game mainGame = new Game(left, right);
        MCTS engine = new MCTS();
        while(mainGame.getStatus()==Game.PROGRESS){
           try{
               mainGame = engine.getNextMove(mainGame,pokemonNum);
               mainGame.printHP();
           } catch (MCTSException e){
               e.getCause();
           }
           //TODO ADD MORE LOGS
           pokemonNum = 1 - pokemonNum;
        }
        SingleFightLog log = new SingleFightLog(leftPokemon.getPokemonType(), rightPokemon.getPokemonType());
        log.addLog(mainGame);
        return log;
    }
}


