package sample.model.datamodels;

public class Result {
    public String name;
    public String url;

    public Result(Result type) {
        this.name = type.name;
        this.url = type.url;
    }
    public Result(){
        this.name="";
        this.url="";
    }
}