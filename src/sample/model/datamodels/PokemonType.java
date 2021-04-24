package sample.model.datamodels;

public class PokemonType {
    private final int id;
    private final int height;
    private final int weight;
    private final String name;
    private final TypeResult[] types;

    PokemonType() {
        id = weight = height = 0;
        name = "";
        types = new TypeResult[0];
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

    public Result[] getTypes() {
        Result[] typesArr = new Result[types.length];
        for (int i = 0; i < types.length; i++)
            typesArr[i] = types[i].type;
        return typesArr;
    }
}


