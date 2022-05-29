package com.monstersaku.util;

public abstract class Move {
    private int id;
    private String name;
    private ElementType elementType;
    private int accuracy;
    private int priority;
    private int ammunition;
    private int maxAmmunition;
    private Target target;
    private MoveType moveType;

    public Move(int id, MoveType moveType,String name, ElementType elementType, int accuracy, int priority, int ammunition, Target target) {
        this.id = id;
        this.name = name;
        this.elementType = elementType;
        this.accuracy = accuracy;
        this.priority = priority;
        this.ammunition = ammunition;
        this.moveType = moveType;
        this.maxAmmunition = ammunition;
    }
    public int getMaxAmmunition(){
        return this. maxAmmunition;
    }
   
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ElementType getElementType() {
        return this.elementType;
    }

    public void setElementType(ElementType elementType) {
        this.elementType = elementType;
    }

    public int getAccuracy() {
        return this.accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getAmmunition() {
        return this.ammunition;
    }

    public void setAmmunition(int ammunition) {
        this.ammunition = ammunition;
    }

    public Target getTarget() {
        return this.target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }
    public abstract void execute(Monster source, Monster target); 
}
