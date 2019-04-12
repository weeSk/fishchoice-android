package com.tecnatox.fishchoice.fish;

import java.util.ArrayList;
import java.util.Hashtable;

public class FishLibrary {
    private static final FishLibrary ourInstance = new FishLibrary();

    public ArrayList<Fish> library = new ArrayList<Fish>();
    public ArrayList<Fish> userLibrary = new ArrayList<Fish>();
    public User u;

    public void add(Fish f){
        library.add(f);
    }

    public void setUser(User u){
        this.u = u;
    }

    public ArrayList<Fish> getLibrary(){
        return library;
    }

    public void addFishToUserLibrary(Fish f, Integer qty){
        if (userLibrary.contains(f)){
            f.setQuantity(f.getQuantity()+qty);
        }else{
            userLibrary.add(f);
            f.setQuantity(qty);
        }
    }

    public int calcNutris(int pos){
        int i=0, totalnutri=0;
        for(i=0; i<userLibrary.size(); i++){
            totalnutri += userLibrary.get(i).getNutrients(pos);
        }
        return totalnutri;

    }


    public Fish getFishByID(int i){
            return library.get(i);
    }


    public static FishLibrary getInstance() {
        return ourInstance;
    }

    private FishLibrary() {
    }
}
