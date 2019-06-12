package com.tecnatox.fishchoice.fish;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Fish {
    private String name;
    private int quantity;
    private String description;
    private ArrayList<Nutrient> nutrients;
    private ArrayList<Pollutant> pollutants;

    public Fish(String name, String description, ArrayList<Nutrient> n) {
        this.name = name;
        this.description = description;
        this.quantity = 0;
        nutrients = n;
        FishLibrary fl = FishLibrary.getInstance();
        fl.add(this);
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName(){
        return name;
    }

    public Float getMgOfNutrient(int i){
        return nutrients.get(i).getMg() * (quantity/100);
    }

    public int getQuantity() {
        return quantity;
    }
}
