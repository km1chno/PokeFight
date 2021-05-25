package sample.model.datamodels;

public class Move {
    private final String name;
    private final int accuracy;
    private final int power;
    private final int pp;
    Result type;

    Move() {
        name = "";
        accuracy = power = pp = 0;
        type = new Result();
    }

    public String getName() {
        return name;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public int getPower() {
        return power;
    }

    public int getPowerPoints() {
        return pp;
    }

    public String getTypeName() {
        return type.name;
    }
}
