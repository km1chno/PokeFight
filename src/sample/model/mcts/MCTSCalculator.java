package sample.model.mcts;

import java.util.ArrayList;

public class MCTSCalculator {
    public static double value(int totalVisit, int nodeVisit, double score){
        if(nodeVisit==0) return Integer.MAX_VALUE;
        return ((double) score / (double) nodeVisit) + Math.sqrt(2) * Math.sqrt(Math.log(totalVisit) / (double) nodeVisit);
    }

    public static Node bestNode(Node node){
        int parentVisitCnt = node.getState().getVisitCnt();
        ArrayList<Node> nextNodeAr = node.getNextNodes();
        double res=Integer.MIN_VALUE;
        Node bestNode=null;
        for (Node value : nextNodeAr) {
            double temp = value(parentVisitCnt, value.getState().getVisitCnt(),
                    value.getState().getWinScore());
            if (temp >= res) {
                res = temp;
                bestNode = value;
            }
        }
        return bestNode;
    }
}
