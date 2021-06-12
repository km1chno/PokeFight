package sample.model.mcts;

public class TreeRoot {
    Node root;

    TreeRoot(){
        root=null;
    }

    public Node getRoot(){
        return root;
    }

    public void setRoot(Node setRoot){
        root=setRoot;
    }
}

