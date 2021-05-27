package sample.model.fetchers;

import com.google.gson.Gson;
import sample.controllers.SceneSwitchController;
import sample.model.datamodels.PokemonType;
import sample.model.exceptions.HttpException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AbstractFetcher implements Fetcher {
    protected Class<?> dataClass;

    @Override
    public String fetch(String urlString) throws HttpException {
        URL url;

        try {
            url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            int status = connection.getResponseCode();

            if (status >= 400) {
                throw new HttpException(status);
            }

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
        } catch (IOException e) {
            SceneSwitchController.handleException(e);
        }
        return "";
    }

    @Override
    public Object parse(String content) {
        Gson gson = new Gson();
        return gson.fromJson(content, dataClass);
    }
}
