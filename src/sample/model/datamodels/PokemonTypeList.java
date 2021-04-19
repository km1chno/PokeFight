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

    public int getCount() {
        return results.length;
    }

    public String getName(int i) {
        if (i >= count)
            throw new RuntimeException();
        return results[i].name;
    }

    public String getUrl(int i) {
        if (i >= count)
            throw new RuntimeException();
        return results[i].url;
    }
}
