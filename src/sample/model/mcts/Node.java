package sample.model.mcts;

import sample.model.fight.SimulatedPokemon;

import java.util.ArrayList;
import java.util.Random;

public class Node {
    Node parent;
    State state;
    ArrayList<Node> nextNodes;
    int id;

    Node(SimulatedPokemon main, SimulatedPokemon op){
        parent=null;
        state= new State(main, op);
        nextNodes = new ArrayList<>();
        id=MCTS.coo++;
    }

    Node(Node node){
        if(node.parent != null){
            this.parent = node.getParent();
        }
        this.nextNodes = new ArrayList<>();
        this.state = new State(node.getState());
        ArrayList<Node> tempAr = node.getNextNodes();
        for(Node n: tempAr){
            this.nextNodes.add(new Node(n));
        }
        id=MCTS.coo++;
    }

    Node(State state){
        parent = null;
        nextNodes = new ArrayList<>();
        this.state = state;
        id=MCTS.coo++;
    }

    public State getState(){
        return state;
    }

    public void setState(State setState){
        state=setState;
    }

    public Node getParent(){
        return parent;
    }

    public void setParent(Node setParent){
        parent=setParent;
    }

    public ArrayList<Node> getNextNodes(){
       return nextNodes;
    }

    public Node getRandomNextNode(){
        int i = new Random().nextInt(getNextNodes().size());
        return getNextNodes().get(i);
    }
    public Node getMaxScoreNode(){
        int cnt=-1;
        Node res=null;
        for(Node n: nextNodes){
            n.getState().getGame().printHP();
            if(n.getState().getVisitCnt()>cnt){
                cnt = n.getState().getVisitCnt();
                res = n;
            }
        }
        return res;
    }
}
