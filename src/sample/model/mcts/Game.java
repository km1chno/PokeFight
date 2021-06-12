package sample.model.mcts;



public class Game {
    public static int DRAW = 2;
    public static int PROGRESS = 3 ;
    int status;

    Game(Game game){
        this.status=game.status;
    }

    public int getStatus(){
        return status;
    }
}
