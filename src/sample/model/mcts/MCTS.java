package sample.model.mcts;

import sample.model.exceptions.MCTSException;

import java.util.List;

public class MCTS {
    static final int timeBudget = 20;
    static final int winScore = 5;
    int enemyPokemon;

    public Game getNextMove(Game game, int pokemonNum) throws MCTSException {
        enemyPokemon = 1 - pokemonNum;
        TreeRoot root = new TreeRoot();
        Node rootNode = root.getRoot();
        rootNode.getState().setGame(game);
        rootNode.getState().setPokemonNum(pokemonNum);
        long endTime= System.currentTimeMillis()+timeBudget;
        while(System.currentTimeMillis() < endTime){
            Node expansionNode = selectionRoute(rootNode);
            if(expansionNode.getState().getGame().getStatus()==Game.PROGRESS){
                expandNode(expansionNode);
            }
            Node randomNode;
            if(expansionNode.nextNodes.size()>0){
                randomNode = expansionNode.getRandomNextNode();
            }
            else{
                randomNode = expansionNode;
            }
            int simulationResult = simulateRandomNode(randomNode);
            propagation(randomNode, simulationResult);
        }
        Node chosenNode = rootNode.getMaxScoreNode();
        if(chosenNode == null){
            //TODO
            throw new MCTSException();
        }
        return chosenNode.getState().getGame();
    }

    public void expandNode(Node node){
        List<State> possibleStates = node.getState().getPossibleStates();
        for(State s: possibleStates){
            Node temp = new Node(node.getState());
            temp.setState(node.getState());
            temp.setParent(node.getParent());
            temp.getState().setPokemonNum(node.getState().getEnemy());
            node.getNextNodes().add(temp);
        }
    }

    public Node selectionRoute(Node start){
        Node temp = start;
        while(temp.getNextNodes().size()>0){
            temp = MCTSCalculator.bestNode(temp);
        }
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
