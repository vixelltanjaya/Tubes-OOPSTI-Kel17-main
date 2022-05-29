package com.monstersaku.util;
import java.util.*;

public class StatusMove extends Move {
    private Target target;
    private StatusCondition statusCondition;
    private Stats moveStats;
    private double hp; 
    private double atk;
    private double def;
    private double spcatk;
    private double spcdef;
    private double speed;

    public StatusMove (int id,MoveType moveType ,String name, ElementType elementType, int accuracy, int priority, int ammunition, Target target, StatusCondition statusCondition, double hp, double atk, double def, double spcatk, double spcdef, double speed) {
        super(id,moveType ,name, elementType, accuracy, priority, ammunition, target);
        this.target = target;
        this.statusCondition = statusCondition;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.spcatk = spcatk;
        this.spcdef = spcdef;
        this.speed = speed;
        
    }

    public Target getTarget() {
        return this.target;
    }

    public StatusCondition getStatusCondition() {
        return this.statusCondition;
    }

    public Stats getMoveStats() {
        return this.moveStats;
    }

    public void execute (Monster source, Monster target) {
        Random random = new Random();
        
        int par = random.nextInt(4);
        if (source.getStatus().equals(StatusCondition.PARALYZE)) {
            // PARALYZE
            Stats base = source.getBaseStats();
            base.setSpeed(base.getMaxSpeed() * 0.5);
            source.setBaseStats(base);
            if (par == 0){
                System.out.println(source.getNama() +" tidak bisa bergerak karena paralyze");
            }
        }
        
        if (par!=0 || !source.getStatus().equals(StatusCondition.PARALYZE)){
        
            int acu = ((random.nextInt(100))+1);
            int sleep = ((random.nextInt(7))+1);
            if (acu <= this.getAccuracy()){ // tidak miss
                if (this.target.equals(Target.OWN)){
                    if ((source.getBaseStats().getHP()+hp) < (source.getBaseStats().getMaxHP())){
                        source.getBaseStats().setHP(source.getBaseStats().getHP()+hp);
                    }
                    else{
                        source.getBaseStats().setHP(source.getBaseStats().getMaxHP());
                    }
                    System.out.println(target.getNama()+" heal for "+hp);
                    
                }
                else if (this.target.equals(Target.ENEMY)){
                    target.getBaseStats().setHP(target.getBaseStats().getHP()+hp);
                    target.getBaseStats().setAttack(target.getBaseStats().getAttackPoint()+atk);
                    target.getBaseStats().setDefense(target.getBaseStats().getDefensePoint()+def);
                    target.getBaseStats().setSpecialAttackPoint(target.getBaseStats().getSpecialAttackPoint()+spcatk);
                    target.getBaseStats().setSpecialDefensePoint(target.getBaseStats().getSpeed()+spcdef);
                    target.getBaseStats().setSpeed(target.getBaseStats().getSpeed()+speed);
                    if ((target.getStatus().equals(StatusCondition.NONE)) && (!(this.statusCondition.equals("-")))){
                        if (this.statusCondition.equals(StatusCondition.BURN)){
                            target.setStatus(StatusCondition.BURN);
                            System.out.println(target.getNama()+" Is Burned");
                        }
                        else if (this.statusCondition.equals(StatusCondition.POISON)){
                            target.setStatus(StatusCondition.POISON);
                            System.out.println(target.getNama()+" Is Poisoned");
                        }
                        else if (this.statusCondition.equals(StatusCondition.SLEEP)){
                            target.setStatus(StatusCondition.SLEEP);
                            target.setSleepCount(sleep);
                            System.out.println(target.getNama()+" Falling a sleep");
                        }
                        else if (this.statusCondition.equals(StatusCondition.PARALYZE)){
                            target.setStatus(StatusCondition.PARALYZE);
                            System.out.println(target.getNama()+" Is Paralyze");
                        }
                    }
                }
            }
            else{
                System.out.println(source.getNama()+" miss");
            }
            this.setAmmunition(this.getAmmunition()-1);
        }
    }
}