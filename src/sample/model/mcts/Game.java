package sample.model.mcts;

import sample.model.datamodels.Move;
import sample.model.datamodels.PokemonInstance;
import sample.model.datamodels.Status;
import sample.model.datamodels.Type;
import sample.model.fight.SimulatedPokemon;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

public class Game {
    public static int LEFT = 0;
    public static int RIGHT = 1;
    public static int DRAW = 2;
    public static int PROGRESS = 3;
    private final int turnsOffset = 13;
    int status;
    int turnsWithoutDmg;
    public SimulatedPokemon left, right;

    ArrayList<String> lastMoveLogs;


    public Game(Game game){
        this.status=game.status;
        this.left = new SimulatedPokemon(game.left);
        this.right = new SimulatedPokemon(game.right);
        turnsWithoutDmg = 0;
        lastMoveLogs = new ArrayList<>();
    }

   public Game(SimulatedPokemon left, SimulatedPokemon right){
        this.left = new SimulatedPokemon(left);
        this.right = new SimulatedPokemon(right);
        status = PROGRESS;
        turnsWithoutDmg = 0;
        lastMoveLogs = new ArrayList<>();

    }

    SimulatedPokemon getPokemon(int i){
        return switch (i) {
            case 0 -> left;
            case 1 -> right;
            default -> null;
        };
    }

    SimulatedPokemon getEnemy(int i){
        int j=1-i;
        return switch (j) {
            case 0 -> left;
            case 1 -> right;
            default -> null;
        };
    }

    public int getMovesNumber(int pokemonNum){
        return switch (pokemonNum){
            case 0 -> left.getMoves().length;
            case 1 -> right.getMoves().length;
            default -> throw new IllegalStateException("Unexpected value: " + pokemonNum);
        };
    }

    public void move(int pokemonNum, int moveNum){
        //TODO add logs
        String myName=getPokemon(pokemonNum).getPokemonType().getName();
        String opName=getEnemy(pokemonNum).getPokemonType().getName();
        lastMoveLogs.clear();
        if(moveNum==-1){
            //do nothing
            turnsWithoutDmg++;
            lastMoveLogs.add( myName + " has no power points and does nothing");
            return;
        }
        Random random = new Random();
        TreeSet<Status> appliedStatuses = getPokemon(pokemonNum).processStatus();
        for(Status s: appliedStatuses){
            if(s!=Status.BURN  && s!=Status.POISON)
                lastMoveLogs.add(myName + " suffers from " + s.name());
            else{
                turnsWithoutDmg=0;
                if(s == Status.BURN) lastMoveLogs.add(myName + " is burning for " + getPokemon(pokemonNum).getHp()/10 +"damage" );
                else lastMoveLogs.add(myName + " is poisoned and lost " + getPokemon(pokemonNum).getHp()/9 +" hp" );
            }
        }
        if(getPokemon(pokemonNum).stunned){
            getPokemon(pokemonNum).stunned=false;
            if(!(appliedStatuses.contains(Status.BURN) || appliedStatuses.contains(Status.POISON)))
                turnsWithoutDmg++;
            return;
        }

        if(getStatus()!=Game.PROGRESS){
            return;
        }
        Move activeMove = getPokemon(pokemonNum).getMoves()[moveNum];

        //PP

        getPokemon(pokemonNum).decrementPP(moveNum);

        //Status

        if(activeMove.getMeta().getAilment().getStatus()!=Status.NONE){
            if(random.nextInt(100)<activeMove.getMeta().getAilmentChange()){
                getEnemy(pokemonNum).addStatus(activeMove);
                lastMoveLogs.add(myName + " affects " + opName + " with "
                        + activeMove.getMeta().getAilment().getStatus().name());
            }
        }

        //Buffs Debuffs
        if(activeMove.getStatChanges() != null) {
            System.out.println(activeMove.getStatChanges().toString());
            for (int i = 0; i < activeMove.getStatChanges().length; i++) {
                System.out.println(activeMove.getStatChanges().length);
                if (activeMove.getStatChanges()[i].getIndex() >= 1 && activeMove.getStatChanges()[i].getIndex() <= 4) {
                    if (random.nextInt(100) < activeMove.getMeta().getStatChance()) {
                        if (activeMove.getStatChanges()[i].getChange() > 0) {
                            getPokemon(pokemonNum).affectBuffDebuff(activeMove, i);
                            lastMoveLogs.add(myName + " buffs itself");
                        } else {
                            getEnemy(pokemonNum).affectBuffDebuff(activeMove, i);
                            lastMoveLogs.add(myName + " debuffs " + opName );
                        }
                    }
                }
            }
        }

        //Healing

        if(activeMove.getMeta().getHealing()>0){
            int healVal=getPokemon(pokemonNum).getHp()*(activeMove.getMeta().getHealing()/100);
            getPokemon(pokemonNum).heal(healVal);
            lastMoveLogs.add(myName + " heals for " + healVal + "percent max health");
        }

        //Damage

        double crit;
        if(random.nextInt(256)>215) crit=(double)(2*getPokemon(pokemonNum).getLvl()+5)/(double)(getPokemon(pokemonNum).getLvl()+5);
        else crit=1;
        int A, D;
        if(activeMove.getType()== Type.NORMAL || activeMove.getType()== Type.FIGHTING || activeMove.getType()== Type.FLYING
                || activeMove.getType()== Type.POISON || activeMove.getType()== Type.GROUND || activeMove.getType()== Type.ROCK
            || activeMove.getType()== Type.BUG || activeMove.getType()== Type.GHOST || activeMove.getType()== Type.STEEL){
            A=getPokemon(pokemonNum).getFinalAttack();
            D=getEnemy(pokemonNum).getFinalDefence();
        }
        else{
            A=getPokemon(pokemonNum).getFinalSpAttack();
            D=getPokemon(pokemonNum).getFinalSpDefence();
        }
        int rndDmg= random.nextInt(15);
        double rndMdf=(double)(rndDmg+85)/100;
        int dmg=(int)(((((2*(getPokemon(pokemonNum).getLvl()/5)+2)*activeMove.getPower()*A/D)/50+2)*crit)*rndMdf);

        getEnemy(pokemonNum).dealDMG(dmg);
        if(dmg==0) turnsWithoutDmg++;
        else turnsWithoutDmg=0;
        lastMoveLogs.add(myName + " deals " + dmg + " damage  to " + opName +  " with " + activeMove.getName());
        //get dmg log with crit info
    }

    public int getStatus(){
        if(left.getFinalHp()<=0 && right.getFinalHp()<=0){
            //i don't know if status effects can be bugged but let's define that situation as a draw
            return Game.DRAW;
        }
        else if(left.getFinalHp()<=0){
            return Game.RIGHT;
        }
        else if(right.getFinalHp()<=0){
            return Game.LEFT;
        }
        else if(left.sumPP()==0 && right.sumPP()==0){
            return Game.DRAW;
        }
        else if(turnsWithoutDmg >= turnsOffset){
            return Game.DRAW;
        }
        else{
            return Game.PROGRESS;
        }
    }

    public ArrayList<String> getLogs(){
        if(!lastMoveLogs.isEmpty())
            return lastMoveLogs;
        else
            return null;
    }

    public void printHP(){
        System.out.println(left.getFinalHp()+ " " + right.getFinalHp());
    }
    public void printPP(){
        System.out.println(left.sumPP()  +  " " + right.sumPP());
    }
}
