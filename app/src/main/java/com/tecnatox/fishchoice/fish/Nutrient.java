package com.tecnatox.fishchoice.fish;

import com.tecnatox.fishchoice.fish.utils.Units;

public class Nutrient {

    private String name;
    private Integer amount;
    private Units units;

    public Nutrient(String name, Integer amount, Units units) {
        this.name = name;
        this.amount = amount;
        this.units = units;
    }

    public Integer getAmount() {
        return amount;
    }
}
