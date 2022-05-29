package com.monstersaku.util;

public class Stats {
    private double healthPoint;
    private double attackPoint;
    private double defensePoint;
    private double specialAttackPoint;
    private double specialDefensePoint;
    private double speedPoint;
    private double maxHP;
    private double maxSpeed;
    private double maxDamage;

    public Stats(double hp, double attack, double defend, double Sp_Attack ,double Sp_Defense, double speed){
        this.healthPoint = hp;
        this.attackPoint = attack;
        this.defensePoint = defend;
        this.specialAttackPoint = Sp_Attack;
        this.specialDefensePoint = Sp_Defense;
        this.speedPoint = speed;
        this.maxHP = hp;
        this.maxSpeed = speed;
        this.maxDamage = attack;
    }

    public void basestats(){
        System.out.println("_|_|_|      _|_|      _|_|_|  _|_|_|_|        _|_|_|  _|_|_|_|_|    _|_|    _|_|_|_|_|    _|_|_|");
        System.out.println("_|    _|  _|    _|  _|        _|            _|            _|      _|    _|      _|      _|      ");
        System.out.println("_|_|_|    _|_|_|_|    _|_|    _|_|_|          _|_|        _|      _|_|_|_|      _|        _|_|  ");
        System.out.println("_|    _|  _|    _|        _|  _|                  _|      _|      _|    _|      _|            _|");
        System.out.println("_|_|_|    _|    _|  _|_|_|    _|_|_|_|      _|_|_|        _|      _|    _|      _|      _|_|_|  ");
    }
    public double getHP() {
        return this.healthPoint;
    }
    public double getAttackPoint() {
        return this.attackPoint;
    }
    public double getSpeedPoint() {
        return this.speedPoint;
    }
    public double getDefensePoint() {
        return this.defensePoint;
    }
    public double getSpecialAttackPoint() {
        return  this.specialAttackPoint;
    }
    public double getSpecialDefensePoint(){
        return this.specialDefensePoint;
    }
    public double getSpeed(){
        return this.speedPoint;
    }
    public double getMaxHP(){
        return this.maxHP;
    }
    public double getMaxSpeed(){
        return this.maxSpeed;
    }
    public double getMaxDamage(){
        return this.maxDamage;
    }
    public void setHP(double hp){
        this.healthPoint = hp;
    }
    public void setAttack(double attack){
        this.attackPoint = attack;
    }
    public void setDefense(double defend){
        this.defensePoint = defend;
    }
    public void setSpecialAttackPoint(double Sp_Attack){
        this.specialAttackPoint = Sp_Attack;
    }
    public void setSpecialDefensePoint(double Sp_Defense){
        this.specialDefensePoint = Sp_Defense;
    }
    public void setSpeed(double speed){
        this.speedPoint = speed;
    }
    public void diserang(double damage) {
        this.healthPoint = this.healthPoint - damage;
    }
    public void StatStatus(){
        
        basestats();
        System.out.println("HP: " + this.healthPoint+" / " + this.maxHP);
        System.out.println("Attack: " + this.attackPoint);
        System.out.println("Defense: " + this.defensePoint);
        System.out.println("Sp.Attack: " + this.specialAttackPoint);
        System.out.println("Sp.Defense: " + this.specialDefensePoint);
        System.out.println("Speed: " + this.speedPoint);
    }
}
