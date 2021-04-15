package sample.model.datamodels;

public class PokemonType {
    private final int id;
    private final int height;
    private final int weight;
    private final String name;
    private final Sprites sprites;

    PokemonType() {
        id = weight = height = 0;
        name = "";
        sprites = new Sprites();
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

    public Sprites getSprites() { return sprites; }
}


