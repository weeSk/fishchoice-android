package com.tecnatox.fishchoice.fish;

import com.tecnatox.fishchoice.fish.utils.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Hashtable;

public class FishLibrary {

    private static final FishLibrary ourInstance = new FishLibrary();

    //AllSpiciesRecyclerViewAdapter
    public RecyclerViewAdapter asAdapter;

    //Full library of seafood species
    public ArrayList<Fish> library = new ArrayList<Fish>();

    //Library of selected seafood species by the user
    public ArrayList<Fish> userLibrary = new ArrayList<Fish>();

    //List of seafood species frequently used
    public ArrayList<Fish> frequents = new ArrayList<Fish>();

    //Information of the user to calculate with
    public User u;

    //Hashtable with nutrients minimum requirements
    private Hashtable<Integer, Float> nutrients_req;
    private Hashtable<Integer, String> nutrients_name;

    public void add(Fish f){
        library.add(f);
    }

    public void setUser(User u){
        this.u = u;
    }

    public void setNutrientsRequirements(Hashtable<Integer, Float> nr){
        this.nutrients_req = new Hashtable<>(nr);
    }

    public void addFishToUserLibrary(Fish f, Integer qty){

        //Handle if fish is already in the selections of user
        if (userLibrary.contains(f)){
            f.setQuantity(f.getQuantity()+qty);
        }else{
            userLibrary.add(f);
            f.setQuantity(qty);
            if (!frequents.contains(f)){frequents.add(f);}
        }
    }

    public void setNutrientsName(Hashtable<Integer, String> nn){
        this.nutrients_name = new Hashtable<>(nn);
    }

    public ArrayList<Fish> getLibrary(){
        return library;
    }

    public Float getNutrientsReq(int i) {
        return nutrients_req.get(i);
    }

    public String getNutrientsName(int i) {
        return nutrients_name.get(i);
    }

    //Function to get the summation of the nutrients values of each food.
    public ArrayList<Float> calcNutris(){
        ArrayList<Float> allNutrients = new ArrayList<Float>();
        for(int i = 0; i<userLibrary.size(); i++){
            for(int j = 0; j<nutrients_name.size(); j++){
                try{
                    allNutrients.set(j, allNutrients.get(j) + userLibrary.get(i).getMgOfNutrient(j));
                }catch (IndexOutOfBoundsException e){
                    allNutrients.add(j, userLibrary.get(i).getMgOfNutrient(j));
                }
            }
        }
        return allNutrients;
    }

    public Fish getFishByID(int i){
            return library.get(i);
    }

    public static FishLibrary getInstance() {
        return ourInstance;
    }

}
