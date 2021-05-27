package sample.model.fetchers;

import sample.model.datamodels.PokemonTypeList;

public class PokemonTypeListFetcher extends AbstractFetcher {

    public PokemonTypeListFetcher() {
        dataClass = PokemonTypeList.class;
    }
}
