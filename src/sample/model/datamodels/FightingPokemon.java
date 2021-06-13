package sample.model.datamodels;

public enum FightingPokemon{
    LEFT,
    RIGHT,
    NONE;
    public FightingPokemon opposite() {
        if (this == LEFT) return RIGHT;
        if (this == RIGHT) return LEFT;
        return NONE;
    }
}
