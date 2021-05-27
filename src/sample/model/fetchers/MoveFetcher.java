package sample.model.fetchers;

import sample.model.Constants;
import sample.model.datamodels.Move;
import sample.model.exceptions.HttpException;

public class MoveFetcher extends AbstractFetcher {

    public MoveFetcher() {
        dataClass = Move.class;
    }

    public String fetchFromName(String moveName) throws HttpException {
        return fetch(Constants.API_BASE_URL + "move/" + moveName);
    }

    public Object fetchAndParseFromName(String moveName) throws HttpException { return parse(fetchFromName(moveName)); }
}
