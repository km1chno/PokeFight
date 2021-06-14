package sample.model.datamodels;

public class Stat extends Result {
    public int getId() { return Integer.parseInt(url.substring(url.length() - 2, url.length() - 1)) - 1; }
}
