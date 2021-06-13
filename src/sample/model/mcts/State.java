package sample.model.mcts;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class State {
    Game game;
    int pokemonNum;
    int visitCnt;
    double winScore;

    State(State state){
        this.pokemonNum = state.pokemonNum;
        this.visitCnt = state.visitCnt;
        this.winScore = state.winScore;
        this.game = new Game(state.getGame());
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
        return null;
    }


    public void fight(){
        int d=game.getMovesNumber(pokemonNum);
        int rnd=new Random().nextInt(d);
        game.move(pokemonNum, d);
    }
}
