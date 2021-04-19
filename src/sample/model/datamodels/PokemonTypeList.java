package sample.model.datamodels;

import sample.model.providers.PokemonTypeListFilter;

public class PokemonTypeList {
    private int count;
    Result[] results;

    PokemonTypeList() {
        count = 0;
        results = new Result[0];
    }

    PokemonTypeList(int count, Result[] results) {
        this.count = count;
        this.results = results;
    }

    public int getCount() {
        return results.length;
    }

    public String getName(int i) {
        if (i < 0 || i >= count)
            throw new RuntimeException();
        return results[i].name;
    }

    public String getUrl(int i) {
        if (i < 0 || i >= count)
            throw new RuntimeException();
        return results[i].url;
    }

    public int getId(int i) {
        if (i < 0 || i >= count)
            throw new RuntimeException();
        int id = 0;
        String tmp = results[i].url;

        int j = tmp.lastIndexOf('/');
        int k = tmp.substring(0, tmp.length()-1).lastIndexOf('/');
        return Integer.parseInt(tmp.substring(k+1, j));
    }

    public PokemonTypeList filter(int count, PokemonTypeListFilter f) {
        int newCount = 0;
        for (Result res : results) {
            if (f.test(res))
                newCount++;
        }
        newCount = Math.min(newCount, count);
        Result[] newResult = new Result[newCount];
        int it = 0;
        for (Result res : results) {
            if (it >= newCount)
                break;
            if (f.test(res))
                newResult[it++] = res;
        }
        return new PokemonTypeList(newCount, newResult);
    }
}
