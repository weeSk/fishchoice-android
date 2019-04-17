package com.tecnatox.fishchoice.fish;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Fish {
    private String name;
    private int quantity;
    private String description;
    private ArrayList<Nutrient> nutrients;
    private Hashtable<String,Integer> pollutants;

    public Fish(String name, String description, ArrayList<Nutrient> n) {
        this.name = name;
        this.description = description;
        this.quantity = 0;
        nutrients = n;
        FishLibrary fl = FishLibrary.getInstance();
        fl.add(this);
    }

    public String getName(){
        return name;
    }

    public Float getNutrients(int i){
        return nutrients.get(i).getMg();
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

    public Float getMgOfNutrient(int i){
        return nutrients.get(i).getMg() * (quantity/100);
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
