package sample.model.mcts;

import sample.model.fight.SimulatedPokemon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class State {
    Game game;
    int pokemonNum;
    int visitCnt;
    double winScore;

    State(SimulatedPokemon main, SimulatedPokemon op){
        game = new Game(main, op);
    }

    State(State state){
        this.pokemonNum = state.pokemonNum;
        this.visitCnt = state.visitCnt;
        this.winScore = state.winScore;
        this.game = new Game(state.getGame());
    }

    public State(Game game) {
        this.game = new Game(game);
        visitCnt=0;
        winScore=0;
    }

    public Game getGame(){
        return game;
    }

    public void setGame(Game setGame){
        game = setGame;
    }

    public int getPokemonNum(){
        return pokemonNum;
    }

    public int getEnemy(){
        return 1-getPokemonNum();
    }

    public void setPokemonNum(int setPokemonNum){
        pokemonNum=setPokemonNum;
    }

    public int getVisitCnt(){
        return visitCnt;
    }

    public double getWinScore(){
        return winScore;
    }

    public void setWinScore(double score){
        winScore=score;
    }

    public void swap(){
        pokemonNum = 1-pokemonNum;
    }
    public void addScore(double score){
        if(score>Integer.MIN_VALUE)
            winScore+=score;
    }

    public void addVisit(){
        visitCnt++;
    }

    public ArrayList<State> getPossibleStates(){
        ArrayList<State> ar = new ArrayList<>();
        int d = game.getMovesNumber(pokemonNum);
        for(int i=0; i<d; i++){
            if(game.getPokemon(pokemonNum).getMovePP(i)>0){
                State state = new State(this.game);
                state.setPokemonNum(1-pokemonNum);
                state.getGame().move(state.getPokemonNum(), i);
                state.getGame().printHP();
                ar.add(state);
            }
        }
        return ar;
    }


    public void fight(){
        if(game.getPokemon(pokemonNum).sumPP()==0){
            game.move(pokemonNum, -1); //means that pokemon has no PP
        }
        int d=game.getMovesNumber(pokemonNum);
        Random random =new Random();
        int rnd;
        do {
            rnd = random.nextInt(d);
        }while(game.getPokemon(pokemonNum).getMovePP(rnd)==0);
        game.move(pokemonNum, rnd);
    }
}
