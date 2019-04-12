package com.tecnatox.fishchoice.fish;

import com.tecnatox.fishchoice.fish.utils.Units;

import java.util.ArrayList;
import java.util.Hashtable;

public class Fish {
    private String name;
    private int quantity;
    private String description;
    private float edible;
    private ArrayList<Nutrient> nutrients;
    private Hashtable<String,Integer> pollutants;

    public Fish(String name, String description, float edible, ArrayList<Nutrient> n) {
        this.name = name;
        this.description = description;
        this.edible = edible;
        this.quantity = 0;
        nutrients = n;
        FishLibrary fl = FishLibrary.getInstance();
        fl.add(this);
    }

    public String getName(){
        return name;
    }

    public int getNutrients(int i){
        return nutrients.get(i).getAmount();
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEdible(float edible) {
        this.edible = edible;
    }
}
