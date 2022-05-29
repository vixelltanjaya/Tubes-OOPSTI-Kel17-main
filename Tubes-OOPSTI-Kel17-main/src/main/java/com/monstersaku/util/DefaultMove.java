package com.monstersaku.util;
import java.util.*;

public class DefaultMove extends Move {
    private int basePower;

    public DefaultMove () {
        super(999, MoveType.DEFAULT,"DefaultMove", ElementType.NORMAL, 100, 0, 1,Target.ENEMY);
        this.basePower = 50;
    }

    public int getBasePower() {
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

            double randomNumber = (random.nextInt(-85 + 1 + 100) + 85)/100d;
            // System.out.println(randomNumber);
            double effectivity = 1;
            double burneffect = 1;
            for (ElementType e : target.getElementTypes()) {
                effectivity *= EffectivityPool.getEffectivity(getElementType(), e);
            }
            // System.out.println(effectivity);
            if (source.getStatus().equals(StatusCondition.BURN)) {
                burneffect = 0.5;
            }
            double damage = Math.floor((((getBasePower() * (source.getBaseStats().getAttackPoint() / target.getBaseStats().getDefensePoint())) + 2) * randomNumber * effectivity * burneffect));
            double newHP;
            System.out.println(source.getNama()+" dealt "+ damage);
            double recoil = Math.floor(0.25*(source.getBaseStats().getMaxHP()));
            System.out.println(source.getNama()+" terkena recoil "+recoil);
            double newHP2; 
            newHP = target.getBaseStats().getHP() - damage;
            newHP2 = source.getBaseStats().getHP() - recoil;
            if (newHP <= 0) {
                newHP = 0;
            target.setIsDead(true);
            }
            if (newHP2 <= 0) {
                newHP2 = 0;
                source.setIsDead(true);
            }
            target.getBaseStats().setHP(newHP);
            source.getBaseStats().setHP(newHP2);

        }
        
    }
}