package sample.model.mcts;

import sample.model.fight.SimulatedPokemon;

public class Game {
    public static int DRAW = 2;
    public static int PROGRESS = 3;
    int status;
    SimulatedPokemon left, right;

    public Game(Game game){
        this.status=game.status;
        this.left = new SimulatedPokemon(game.left);
        this.right = new SimulatedPokemon(game.right);
    }

   public Game(SimulatedPokemon left, SimulatedPokemon right){
        this.left = left;
        this.right = right;
        status = PROGRESS;
    }


    SimulatedPokemon getPokemon(int i){
        return switch (i) {
            case 0 -> left;
            case 1 -> right;
            default -> null;
        };
    }

    SimulatedPokemon getEnemy(int i){
        int j=1-i;
        return switch (j) {
            case 0 -> left;
            case 1 -> right;
            default -> null;
        };
    }

    public int getMovesNumber(int pokemonNum){
        return switch (pokemonNum){
            case 0 -> left.getMoves().length;
            case 1 -> right.getMoves().length;
            default -> throw new IllegalStateException("Unexpected value: " + pokemonNum);
        };
    }

    public void move(int pokemonNum, int moveNum){
        
    }

    public int getStatus(){
        return status;
    }
}
