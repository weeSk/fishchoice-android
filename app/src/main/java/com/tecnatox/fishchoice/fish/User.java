package com.tecnatox.fishchoice.fish;

public class User {

    private float weight;

    public User(float weight) {
        this.weight = weight;
        FishLibrary fl = FishLibrary.getInstance();
        fl.setUser(this);
    }

    public float getWeight() {
        return weight;
    }
}
