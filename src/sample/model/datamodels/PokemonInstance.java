package sample.model.datamodels;

import sample.model.exceptions.IncorrectStatsException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PokemonInstance {
    private final PokemonType pokemonType;

    //some moves might be null
    private final Move[] moves;

    //max lvl is 100
    private final int lvl;
    private final int exp;

    //Individual values, these are randomly generated for every pokemon on catch. You can think of them as "genes"
    //all of them fall into range [0, 31]
    private final int IVhp;
    private final int IVattack;
    private final int IVdefense;
    private final int IVspAttack;
    private final int IVspDef;
    private final int IVspeed;

    //Effort value, based on experience and previous fights - here calculated based on current experience with formula:
    //ev = sqrt(exp)/4
    //it's not 100% accurate to games but for this version, good enough
    private final int EV;

    //Stats calculated based on formulas:
    //HP = floor(0.01 x (2 x Base + IV + floor(0.25 x EV)) x Level) + Level + 10
    //Other Stats = floor(0.01 x (2 x Base + IV + floor(0.25 x EV)) x Level) + 5) x Nature
    //which is valid since Generation III

    //NOTE: we ignore Nature value, it's kinda complicated and maybe to implement in further versions
    private final int hp;
    private final int attack;
    private final int defense;
    private final int spAttack;
    private final int spDef;
    private final int speed;

    PokemonInstance() {
        pokemonType = new PokemonType();
        moves = new Move[4];
        lvl = exp = IVhp = IVattack = IVdefense = IVspAttack = IVspDef = IVspeed = EV = hp = attack = defense = spAttack = spDef = speed = 0;
    }

    public PokemonInstance(PokemonType pokemonType, Move[] moves, int lvl, int exp, ArrayList<Integer> IV) throws IncorrectStatsException {
        if (pokemonType == null || moves == null || moves.length != 4 || exp < 0 || lvl > 100 || lvl < 1 || IV == null || IV.size() != 6) {
            throw new IncorrectStatsException();
        }

        for (Integer stat : IV) {
            if (stat < 0 || stat > 31) {
                throw new IncorrectStatsException();
            }
        }

        this.pokemonType = pokemonType;
        this.moves = moves;

        this.lvl = lvl;
        this.exp = exp;

        IVhp = IV.get(0);
        IVattack = IV.get(1);
        IVdefense = IV.get(2);
        IVspAttack = IV.get(3);
        IVspDef = IV.get(4);
        IVspeed = IV.get(5);

        EV = (int) Math.floor(Math.sqrt((double) exp)/4);

        ArrayList<Integer> baseStats = pokemonType.getStatsArray();
        hp = ((int) Math.floor(0.01 * (2 * baseStats.get(0) + IVhp + Math.floor(0.25 * (double) EV)) * lvl)) + lvl + 10;
        attack = (int) Math.floor(0.01 * (2 * baseStats.get(1) + IVattack + Math.floor(0.25 * (double) EV)) * lvl) + 5;
        defense = (int) Math.floor(0.01 * (2 * baseStats.get(2) + IVdefense + Math.floor(0.25 * (double) EV)) * lvl) + 5;
        spAttack = (int) Math.floor(0.01 * (2 * baseStats.get(3) + IVspAttack + Math.floor(0.25 * (double) EV)) * lvl) + 5;
        spDef = (int) Math.floor(0.01 * (2 * baseStats.get(4) + IVdefense + Math.floor(0.25 * (double) EV)) * lvl) + 5;
        speed = (int) Math.floor(0.01 * (2 * baseStats.get(5) + IVspeed + Math.floor(0.25 * (double) EV)) * lvl) + 5;
    }

    public PokemonType getPokemonType() {
        return pokemonType;
    }

    public Move[] getMoves() {
        return moves;
    }

    public List<Integer> getStats() {
        // in order: hp, attack, defense, specialAttack, specialDefense, speed
        return Arrays.asList(hp, attack, defense, spAttack, spDef, speed);
    }

    public void print() {
        System.out.println("Pokemon type name: " + pokemonType.getName());
        System.out.println("Level: " + lvl);
        System.out.println("Experience: " + exp);
        System.out.println("IV stats: " + IVhp + ", " + IVattack + ", " + IVdefense + ", " + IVspAttack + ", " + IVspDef + ", " + IVspeed);
        System.out.println("EV: " + EV);
        System.out.println("Calculated Stats: " + hp + ", " + attack + ", " + defense + ", " + spAttack + ", " + spDef + ", " + speed);
        System.out.print("Moves: ");
        System.out.print(( (moves[0] == null) ? "null, " : moves[0].getName() + ": " + moves[0].getPower() + ", "));
        System.out.print(( (moves[1] == null) ? "null, " : moves[1].getName() + ": " + moves[1].getPower() + ", "));
        System.out.print(( (moves[2] == null) ? "null, " : moves[2].getName() + ": " + moves[2].getPower() + ", "));
        System.out.println(( (moves[3] == null) ? "null" : moves[3].getName() + ": " + moves[3].getPower()));
    }
}
