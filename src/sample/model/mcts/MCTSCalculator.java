package sample.model.mcts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class MCTSCalculator {
    public static double value(int totalVisit, int nodeVisit, double score){
        if(nodeVisit==0) return Integer.MAX_VALUE;
        return ((double) score / (double) nodeVisit) + Math.sqrt(2) * Math.sqrt(Math.log(totalVisit) / (double) nodeVisit);
    }

    public static Node bestNode(Node node){
        int parentVisitCnt = node.getState().getVisitCnt();
        return Collections.max(node.getNextNodes(), Comparator.comparing(n ->value(parentVisitCnt,  n.getState().getVisitCnt(), n.getState().getWinScore())));
    }
}
