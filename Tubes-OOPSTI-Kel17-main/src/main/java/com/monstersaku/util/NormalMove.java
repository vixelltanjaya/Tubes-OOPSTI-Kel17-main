package com.monstersaku.util;
import java.util.*;

public class NormalMove extends Move {
    private double basePower;

    public NormalMove (int id, MoveType moveType,String name, ElementType elementType, int accuracy, int priority, int ammunition,Target target,double basePower) {
        super(id,moveType ,name, elementType, accuracy, priority, ammunition,target);
        this.basePower = basePower;
    }

    public double getBasePower() {
        return this.basePower;
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
            int acu = (random.nextInt(100))+1;
            if (acu <= this.getAccuracy()){ // tidak miss
                double randomNumber = (random.nextInt(-85 + 1 + 100) + 85)/100d;
                // System.out.println(randomNumber);
                double effectivity = 1;
                double burneffect = 1;
                for (ElementType e : target.getElementTypes()) {
                    effectivity *= (EffectivityPool.getEffectivity(this.getElementType(),e));
                }
                // System.out.println(effectivity);
                if (source.getStatus().equals(StatusCondition.BURN)) {
                    burneffect = 0.5;
                }
                double damage = Math.floor(((getBasePower() * (source.getBaseStats().getAttackPoint() / target.getBaseStats().getDefensePoint()) + 2) * randomNumber * effectivity * burneffect));
                System.out.println(source.getNama()+" dealt "+ damage + " damage");
                double newHP;
                newHP = target.getBaseStats().getHP() - damage;
                if (newHP <= 0) {
                newHP = 0;
                target.setIsDead(true);
                }
                target.getBaseStats().setHP(newHP);
                
            }
            else{
                System.out.println(source.getNama()+" miss");
            }
        }
        this.setAmmunition(this.getAmmunition()-1);
    }
}