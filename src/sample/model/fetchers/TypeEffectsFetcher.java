package sample.model.fetchers;

import sample.model.Constants;
import sample.model.datamodels.TypeEffects;
import sample.model.exceptions.HttpException;

public class TypeEffectsFetcher extends AbstractFetcher {

    public TypeEffectsFetcher() {
        dataClass = TypeEffects.class;
    }

    public String fetchFromId(int id) throws HttpException {
        return fetch(Constants.API_BASE_URL + "type/" + id);
    }

    public Object fetchAndParseFromId(int id) throws HttpException { return parse(fetchFromId(id)); }
}
