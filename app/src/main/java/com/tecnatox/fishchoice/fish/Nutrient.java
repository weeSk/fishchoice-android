package com.tecnatox.fishchoice.fish;

public class Nutrient {
    private int id;
    private String name;
    private Float mg;     //mg per 100g of this fish

    public Nutrient(Integer id, String name, Float mg) {
        this.id = id;
        this.name = name;
        this.mg = mg;
    }

    public Float getMg() {
        return mg;
    }
}
