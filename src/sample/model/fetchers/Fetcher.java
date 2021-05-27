package sample.model.fetchers;

import sample.model.exceptions.HttpException;

public interface Fetcher {
    String fetch(String urlString) throws HttpException;

    Object parse(String content);

    default Object fetchAndParse(String urlString) throws HttpException {
        return parse(fetch(urlString));
    }
}
