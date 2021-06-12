package sample.model.datamodels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PokemonType {
    private final int id;
    private final int height;
    private final int weight;
    private final String name;
    private final TypeResult[] types;
    private final StatResult[] stats;
    private final MoveResult[] moves;

    PokemonType() {
        id = weight = height = 0;
        name = "";
        types = new TypeResult[0];
        stats = new StatResult[0];
        moves = new MoveResult[0];
    }

    public int getId() {
        return id;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public Type[] getTypes() {
        Type[] typesArr = new Type[types.length];
        for (int i = 0; i < types.length; i++)
            typesArr[i] = Type.valueOf(types[i].type.name.toUpperCase());
        return typesArr;
    }

    public Map<String, Double> getStats() {
        HashMap<String, Double> map = new HashMap<String, Double>();
        for (StatResult stat: stats)
            map.put(stat.stat.name, (double) stat.base_stat);
        return map;
    }

    public ArrayList<Integer> getStatsArray() {
        ArrayList<Integer> array = new ArrayList<>();
        for (StatResult stat: stats)
            array.add(stat.base_stat);
        return array;
    }

    public ArrayList<MoveResult> getMoves() {
        return new ArrayList<>(Arrays.asList(moves));
    }
}


