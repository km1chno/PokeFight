package sample.model.datamodels;
public class Sprites {
    private final String front_default;

    Sprites() {
        // Bulbasaur
        front_default = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png";
    }

    public String getFrontDefault() {
        return front_default;
    }
}