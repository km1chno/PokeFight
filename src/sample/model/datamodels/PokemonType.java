package sample.model.datamodels;
import com.google.gson.Gson;
import sample.model.exceptions.IncompleteDataException;

public class PokemonType {
    private final int id;
    private final int height;
    private final int weight;
    private final String name;
    //private String species;
    //private String[] types;
    //private String spriteUrl;

    /*public static class Type {
        private int slot;
        private
    };*/

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


