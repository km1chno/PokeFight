package sample.model.datamodels;

public class PokemonType {
    private final int id;
    private final int height;
    private final int weight;
    private final String name;

    PokemonType() {
        id = weight = height = 0;
        name = "";
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
}


