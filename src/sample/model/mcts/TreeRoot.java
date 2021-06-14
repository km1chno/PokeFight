package sample.model.mcts;

import sample.model.fight.SimulatedPokemon;

public class TreeRoot {
    Node root;

    TreeRoot(SimulatedPokemon main, SimulatedPokemon op){ root= new Node(main, op); }

    public Node getRoot(){
        return root;
    }

    public void setRoot(Node setRoot){
        root=setRoot;
    }
}

