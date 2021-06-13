package sample.model.mcts;

import sample.model.exceptions.MCTSException;

import java.util.List;

public class MCTS {
    static final int timeBudget = 20;
    static final int winScore = 5;
    static int coo=0;
    int enemyPokemon;

    public Game getNextMove(Game game, int pokemonNum) throws MCTSException {
        enemyPokemon = 1 - pokemonNum;
        TreeRoot root = new TreeRoot(game.getPokemon(pokemonNum), game.getEnemy(pokemonNum));
        Node rootNode = root.getRoot();
        rootNode.getState().setGame(game);
        rootNode.getState().setPokemonNum(pokemonNum);
        System.out.println(rootNode.id + " id root node");
        long endTime= System.currentTimeMillis()+timeBudget;
        int cnt=0;
        while(System.currentTimeMillis() < endTime){
            System.out.println("ile");
            Node expansionNode = selectionRoute(rootNode);
            System.out.println("ile0.5");
            if(expansionNode.getState().getGame().getStatus()==Game.PROGRESS){
                expandNode(expansionNode);
            }
            System.out.println("ile2");
            Node randomNode;
            if(expansionNode.nextNodes.size()>0){
                randomNode = expansionNode.getRandomNextNode();
            }
            else{
                randomNode = expansionNode;
            }
            int simulationResult = simulateRandomNode(randomNode);
            System.out.println("ile3");
            propagation(randomNode, simulationResult);
            System.out.println("ile4");
            if(cnt==0){
                cnt++;
                for(Node n: rootNode.getNextNodes()){
                    n.getState().getGame().printHP();
                }
            }
        }
        Node chosenNode = rootNode.getMaxScoreNode();
        chosenNode.getState().getGame().printHP();
        if(chosenNode == null){
            //TODO
            throw new MCTSException();
        }
        System.out.println(chosenNode.getState().getWinScore() + "dupa");
        return chosenNode.getState().getGame();
    }

    public void expandNode(Node node){
        List<State> possibleStates = node.getState().getPossibleStates();
        for(State s: possibleStates){
            Node temp = new Node(s);
            temp.setParent(node.getParent());
            temp.getState().setPokemonNum(node.getState().getEnemy());
            node.getNextNodes().add(temp);
            if(node.id==0){
                System.out.println("tu ma byc");
                temp.getState().getGame().printHP();
            }
        }
    }

    public Node selectionRoute(Node start){
        Node temp = start;
        while(temp.getNextNodes().size()>0){
            temp = MCTSCalculator.bestNode(temp);
        }
        System.out.println(temp.id);
        return temp;
    }

    public int simulateRandomNode(Node simulatedNode){
        Node temp = new Node(simulatedNode);
        State simulationState = temp.getState();
        int status = temp.getState().getGame().getStatus();
        if(status == enemyPokemon){
            temp.getParent().getParent().getState().setWinScore(Integer.MIN_VALUE);
            return status;
        }
        while(status == Game.PROGRESS){
            simulationState.swap();
            simulationState.fight();
            status = simulationState.getGame().getStatus();
        }
        return status;
    }

    public void propagation(Node node, int player){
        Node temp = node;
        while (temp != null) {
            temp.getState().addVisit();

            if (temp.getState().getPokemonNum() == player) {
                temp.getState().addScore(winScore);
            }
            temp = temp.getParent();
        }
    }

}
