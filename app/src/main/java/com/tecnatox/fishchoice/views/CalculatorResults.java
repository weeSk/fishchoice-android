package com.tecnatox.fishchoice.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tecnatox.fishchoice.R;
import com.tecnatox.fishchoice.fish.FishLibrary;
import com.tecnatox.fishchoice.fish.Nutrient;

import java.util.ArrayList;

public class CalculatorResults extends AppCompatActivity {
    ProgressBar pb;
    FishLibrary fl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_results);
        pb = findViewById(R.id.progressBar);
        fl = FishLibrary.getInstance();
        calcNutris();
    }

    private void calcNutris(){
        ArrayList<Float> totalNutrients= new ArrayList<>(fl.calcNutris());
        int j=0;
        for(int i=0; i<totalNutrients.size(); i++){
            pb.setMax(Math.round(fl.getNutrientsReq(i)));
            pb.setProgress(Math.round(totalNutrients.get(i)));
            if (totalNutrients.get(i) >= fl.getNutrientsReq(i)){
                j++;

            }
        }
        Toast.makeText(getApplicationContext(), String.valueOf(j), Toast.LENGTH_SHORT).show();
    }
}
