package sample.model.datamodels;

import java.util.ArrayList;

public class Move {

    private final String name;
    private final int accuracy;
    private final int power;
    private final int pp;
    private final MoveMetaData meta;
    Result type;

    public Move() {
        name = "";
        accuracy = power = pp = 0;
        type = new Result();
        meta = new MoveMetaData();
    }

    public Move(Move move) {
        this.name= move.getName();
        this.accuracy=move.getAccuracy();
        this.power=move.getPower();
        this.pp=move.getPowerPoints();
        this.meta=move.getMeta();
        this.type = new Result(move.type);
    }

    public String getName() { return name; }
    public int getAccuracy() { return accuracy; }
    public int getPower() { return power; }
    public int getPowerPoints() { return pp; }
    public Type getType() { return Type.valueOf(type.name.toUpperCase()); }
    public MoveMetaData getMeta() { return meta; }


    public static class MoveMetaData {

        private MoveAilment ailment;
        private int ailment_chance; // PERCENTAGE
        private int stat_chance; // PERCENTAGE
        private int crit_rate; // BONUS TO CRIT RATE
        private int healing; // PERCENTAGE OF MAX HP
        private int drain; // If positive then hp drain, if negative, then recoil damage, PERCENTAGE OF DAMAGE
        private int min_hits; // Minimum number of times the move hits
        private int max_hits; // Maximum number of times the move hits
        private int min_turns; // Minimum number of turns the move takes effect
        private int max_turns; // Maximum number of turns the move takes effect

        public MoveAilment getAilment() { return ailment; }
        public int getAilmentChange() { return ailment_chance; }
        public int getStatChance() { return stat_chance; }
        public int getCritRate() { return crit_rate; }
        public int getHealing() { return healing; }
        public int getDrain() { return drain; }
        public int getMinHits() { return min_hits == 0 ? 1 : min_hits; }
        public int getMaxHits() { return max_hits == 0 ? 1 : max_hits; }
        public int getMinTurns() { return min_turns == 0 ? 1 : min_turns; }
        public int getMaxTurns() { return max_turns == 0 ? 1 : max_turns; }


        public static class MoveAilment extends Result {

            public Status getStatus() {
                try {
                    return Status.valueOf(name.toUpperCase());
                } catch (IllegalArgumentException e) {
                    return Status.NONE;
                }
            }
        }


        public static class MoveStatChange {
            private int change; // ABSOLUTE VALUE (?)
            private Stat stat;

            public int getChange() { return change; }
            public int getIndex() { return stat.getId() - 1; }
        }
    }
}
