package sample.model.fetchers;

import com.google.gson.Gson;
import sample.model.datamodels.Move;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MoveFetcher implements Fetcher {
    private final Class<Move> dataClass = Move.class;

    @Override
    public String fetch(String urlString) {
        URL url;

        try {
            url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            //int status = connection.getResponseCode(); // TO-DO - create exceptions for non-success codes

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            connection.disconnect();

            return content.toString();
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        }
        return "";
    }

    public String fetchFromName(String moveName) {
        return fetch("https://pokeapi.co/api/v2/move/" + moveName);
    }

    public Object parse(String content) {
        Gson gson = new Gson();
        return gson.fromJson(content, dataClass);
    }

    public Object fetchAndParse(String urlString) {
        return parse(fetch(urlString));
    }

    public Object fetchAndParseFromName(String moveName) { return parse(fetchFromName(moveName)); }
}
