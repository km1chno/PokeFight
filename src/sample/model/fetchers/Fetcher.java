package sample.model.fetchers;

import sample.model.exceptions.HttpException;

public interface Fetcher {
    public String fetch(String urlString) throws HttpException;
}
