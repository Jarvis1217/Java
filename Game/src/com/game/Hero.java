package com.game;

public class Hero {

    //Data
    private int Id;
    private String Name;
    private int Blood;
    private int Energy;
    private int Level;
    private int Money;
    private int Strength;

    //Func
    public Hero() {
        this.Level = 1;
        this.Money = 0;
        this.Strength = 100;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getBlood() {
        return Blood;
    }

    public void setBlood(int blood) {
        Blood = blood;
    }

    public int getEnergy() {
        return Energy;
    }

    public void setEnergy(int energy) {
        Energy = energy;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }

    public int getMoney() {
        return Money;
    }

    public void setMoney(int money) {
        Money = money;
    }

    public int getStrength() {
        return Strength;
    }

    public void setStrength(int strength) {
        Strength = strength;
    }
}
