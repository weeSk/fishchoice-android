package com.tecnatox.fishchoice.fish;

public class User {

    private float weight;
    private Integer age;

    public User(float weight, Integer age) {
        this.weight = weight;
        this.age = age;
        FishLibrary fl = FishLibrary.getInstance();
        fl.setUser(this);
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight){
        this.weight = weight;
    }
    public void setHeight(Integer age){
        this.age = age;
    }
}
