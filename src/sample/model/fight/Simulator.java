package sample.model.fight;

import sample.model.datamodels.*;
import sample.model.exceptions.ToManyFightsException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

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


    public PokemonIntelligence comparePokemon(PokemonInstance leftPokemon, PokemonInstance rightPokemon){
        PokemonType leftType = leftPokemon.getPokemonType();
        PokemonType rightType = rightPokemon.getPokemonType();
        Move[] leftMoves = leftPokemon.getMoves();
        Move[] rightMoves = rightPokemon.getMoves();
        ArrayList<Integer> leftEffectiveness = new ArrayList<>();
        ArrayList<Integer> rightEffectiveness = new ArrayList<>();
        for(Move move:leftMoves){
            int res=1;
            Type[] rTypes = rightType.getTypes();
            leftEffectiveness.add(res);
        }
        for(Move move:rightMoves){
            int res=1;
            Type[] lTypes = leftType.getTypes();
            rightEffectiveness.add(res);
        }
        return new PokemonIntelligence(leftEffectiveness, rightEffectiveness);
    }

    public GeneralLogs simulate(PokemonInstance leftPokemon, PokemonInstance rightPokemon){

        GeneralLogs logs = new GeneralLogs(leftPokemon.getPokemonType(), rightPokemon.getPokemonType());

        PokemonIntelligence intelligence=comparePokemon(leftPokemon, rightPokemon);

        for(int i=0; i<numberOfFights; i++){
             SingleFightLog log = singleFight(leftPokemon, rightPokemon, intelligence);
             logs.addResult(log);
        }
        return logs;
    }

    //Very Very bad simulation, need another class for single fight with better data classes
    public SingleFightLog singleFight(PokemonInstance leftPokemon, PokemonInstance rightPokemon, PokemonIntelligence intelligence){
        SingleFightLog log = new SingleFightLog(leftPokemon.getPokemonType(), rightPokemon.getPokemonType());
        ArrayList<Integer> leftStats = new ArrayList<>(leftPokemon.getStats());
        ArrayList<Integer> rightStats = new ArrayList<>(rightPokemon.getStats());
        Move[] leftMoves = leftPokemon.getMoves();
        Move[] rightMoves = rightPokemon.getMoves();
        Integer leftHP=leftStats.get(0);
        Integer rightHP=rightStats.get(0);

        boolean keepFighting = true;
        int roundsWithNoDamage = 0;
        Random random = new Random();
        while (keepFighting) {
            int leftHPbefore = leftHP;
            int rightHPbefore = rightHP;
           // System.out.println(leftHP + " " + rightHP);
            if(leftHP<=0 || rightHP<=0)
                break;
            if (leftStats.get(5)>rightStats.get(5)) {

                int d=intelligence.whatToDo(FightingPokemon.LEFT);
                int ile=Math.abs(random.nextInt())%101;

                //idk what type accuracy is
                if (leftMoves[d].getAccuracy()>ile) {
                    //System.out.println(d);
                    rightHP-=leftMoves[d].getPower()/4;
                    log.addLog(FightingPokemon.LEFT, leftMoves[d], leftMoves[d].getPower());
                }
                if (rightHP<=0) {
                    keepFighting = false;
                    continue;
                }

                d=intelligence.whatToDo(FightingPokemon.RIGHT);

                ile=Math.abs(random.nextInt())%101;
                //idk what type accuracy is

                if (rightMoves[d].getAccuracy()>ile) {
                    leftHP-=rightMoves[d].getPower()/4;
                    log.addLog(FightingPokemon.RIGHT, rightMoves[d], rightMoves[d].getPower());
                }
            }
            else {
                int d=intelligence.whatToDo(FightingPokemon.RIGHT);
                int ile=Math.abs(random.nextInt())%101;

                //idk what type accuracy is
                if(rightMoves[d].getAccuracy()>ile) {
                    leftHP-=rightMoves[d].getPower()/4;
                    log.addLog(FightingPokemon.RIGHT, rightMoves[d], rightMoves[d].getPower());
                }

                if(leftHP<=0) {
                    keepFighting = false;
                    continue;
                }

                d=intelligence.whatToDo(FightingPokemon.LEFT);
                ile=Math.abs(random.nextInt())%101;

                if(leftMoves[d].getAccuracy()>ile) {
                    //System.out.println(d);
                    rightHP-=leftMoves[d].getPower()/4;
                    log.addLog(FightingPokemon.LEFT, leftMoves[d], leftMoves[d].getPower());
                }
            }
            if (leftHPbefore == leftHP && rightHPbefore == rightHP) {
                roundsWithNoDamage++;
            } else {
                roundsWithNoDamage = 0;
            }
            if (roundsWithNoDamage > maxRoundsWithNoDamage) {
                keepFighting = false;
            }
        }
        if (leftHP<=0) {
            log.addLog(FightingPokemon.RIGHT);
        } else if (rightHP<=0) {
            log.addLog(FightingPokemon.LEFT);
        } else {
            log.addLog(FightingPokemon.NONE);
        }
        return log;
    }
}


//Mock only, needs to be refactored, but in this way
class PokemonIntelligence{
    ArrayList<Integer> leftEffectiveness;
    ArrayList<Integer> rightEffectiveness;
    public PokemonIntelligence(ArrayList<Integer> lEffectiveness, ArrayList<Integer> rEffectiveness){
        leftEffectiveness=lEffectiveness;
        rightEffectiveness=rEffectiveness;
    }

    //Very silly for now, when all abilities we have are damage only
    public int whatToDo(FightingPokemon p){
        int res=0;
        Random random = new Random();
        if(p==FightingPokemon.LEFT){
            for(Integer val: leftEffectiveness){
                res+=val;
            }
            int choose = Math.abs(random.nextInt(res- leftEffectiveness.get(0))) + leftEffectiveness.get(0);
            for(int i=0; i< leftEffectiveness.size(); i++){
                choose-=leftEffectiveness.get(i);
                if(choose<=0) return i;
            }
        }
        else{
            for(Integer val: rightEffectiveness){
                res+=val;
            }
            int choose = Math.abs(random.nextInt(res- rightEffectiveness.get(0))) + rightEffectiveness.get(0);
            for(int i=0; i< rightEffectiveness.size(); i++){
                choose-=rightEffectiveness.get(i);
                if(choose<=0) return i;
            }
        }
        return Math.abs(random.nextInt(leftEffectiveness.size()));
    }
}

