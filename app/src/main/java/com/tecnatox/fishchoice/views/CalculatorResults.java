package com.tecnatox.fishchoice.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.tecnatox.fishchoice.R;
import com.tecnatox.fishchoice.fish.FishLibrary;
import com.tecnatox.fishchoice.fish.Nutrient;

import java.util.ArrayList;

public class CalculatorResults extends AppCompatActivity {
    FishLibrary fl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_results);
        fl = FishLibrary.getInstance();
        calcNutris();
    }

    private void calcNutris() {


        int total = fl.calcNutris(0);


    }
}
