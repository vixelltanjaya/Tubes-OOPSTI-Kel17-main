package com.monstersaku.util;

import java.util.List;

import com.monstersaku.util.*;

public class Monster{
    int id;
    String nama;
    List<ElementType> elementTypes;
    Stats baseStats;
    List<Move> moves;
    boolean isDead;
    StatusCondition statusCondition;
    int sleepCounter;



    public Monster(int id, String nama, List<ElementType> elementTypes, Stats baseStats, List<Move> moves, boolean isDead) {
        this.id = id;
        this.nama = nama;
        this.elementTypes = elementTypes;
        this.baseStats = baseStats;
        this.moves = moves;
        this.isDead = isDead;
        this.statusCondition = StatusCondition.NONE;
        this.sleepCounter = 0;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public List<ElementType> getElementTypes() {
        return this.elementTypes;
    }

    public void setElementTypes(List<ElementType> elementTypes) {
        this.elementTypes = elementTypes;
    }

    public Stats getBaseStats() {
        return this.baseStats;
    }

    public void setBaseStats(Stats baseStats) {
        this.baseStats = baseStats;
    }

    public List<Move> getMoves() {
        return this.moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public boolean isIsDead() {
        return this.isDead;
    }

    public boolean getIsDead() {
        return this.isDead;
    }

    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }

    public StatusCondition getStatus() {
        return this.statusCondition;
    }

    public void setStatus(StatusCondition Status) {
        this.statusCondition = Status;
    }

    public int getSleepCount(){
        return this.sleepCounter;
    }

    public void setSleepCount(int sleepCounter){
        this.sleepCounter = sleepCounter;
    }

    public void printMove(){
        for (int i = 0; i<moves.size();i++){
            if (moves.get(i).getName().equals("DefaultMove")){
                System.out.println((i+1)+". "+moves.get(i).getName());
            }
            else{
                System.out.println((i+1)+". "+moves.get(i).getName()+" "+moves.get(i).getAmmunition()+"/"+moves.get(i).getMaxAmmunition());
            }
        }
    }
    
}
