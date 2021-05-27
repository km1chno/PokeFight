package sample.model.fetchers;

import sample.model.Constants;
import sample.model.datamodels.PokemonType;
import sample.model.exceptions.HttpException;

public class PokemonTypeFetcher extends AbstractFetcher {

    public PokemonTypeFetcher() {
        dataClass = PokemonType.class;
    }

    public String fetchFromId(int id) throws HttpException {
        return fetch(Constants.API_BASE_URL + "pokemon/" + id);
    }

    public Object fetchAndParseFromId(int id) throws HttpException {
        return parse(fetchFromId(id));
    }
}
