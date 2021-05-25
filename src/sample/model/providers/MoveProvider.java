package sample.model.providers;

import sample.model.datamodels.Move;
import sample.model.fetchers.MoveFetcher;

import java.util.HashMap;

public class MoveProvider {
    private static HashMap<String, Move> moveHashMap = new HashMap<>();
    private static MoveFetcher moveFetcher = new MoveFetcher();

    private static void fetchData(String moveName) {
        moveHashMap.put(moveName, (Move) moveFetcher.fetchAndParseFromName(moveName));
    }

    public static Move getData(String moveName) {
        if (moveName == null) {
            return null;
        }
        if (!moveHashMap.containsKey(moveName)) {
            fetchData(moveName);
        }
        return moveHashMap.get(moveName);
    }
}
