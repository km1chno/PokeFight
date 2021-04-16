package sample.model.datamodels;

public class PokemonTypeList {
    private int count;
    Results[] results;

    PokemonTypeList() {
        count = 0;
        results = new Results[0];
    }

    private class Results {
        private String name;
        private String url;
    }
}
