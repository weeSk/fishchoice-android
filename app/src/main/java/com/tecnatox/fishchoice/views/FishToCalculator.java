package com.tecnatox.fishchoice.views;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tecnatox.fishchoice.R;
import com.tecnatox.fishchoice.fish.Fish;
import com.tecnatox.fishchoice.fish.FishLibrary;
import com.tecnatox.fishchoice.fish.utils.RecyclerUserSelection;
import com.tecnatox.fishchoice.fish.utils.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class FishToCalculator extends AppCompatActivity {

    TextView ins;
    FishLibrary fl;
    Button calc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_to_calculator);
        FloatingActionButton btnAdd = findViewById(R.id.add);
        ins = findViewById(R.id.instructions);
        fl = FishLibrary.getInstance();
        calc = findViewById(R.id.calculate);
        if(fl.userLibrary.isEmpty()){
            ins.setText("Add your weekly intake of fish before calculate");
        }else{
            initRecyclerView();
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FishToCalculator.this, FoodSelector.class);
                startActivityForResult(intent, 1);
            }
        });

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FishToCalculator.this, CalculatorResults.class));
            }
        });
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.fish_selection_container);
        RecyclerUserSelection adapter = new RecyclerUserSelection(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK){
                if(!fl.userLibrary.isEmpty()) {
                    ins.setVisibility(View.GONE);
                }else{
                    ins.setVisibility(View.VISIBLE);
                }
                initRecyclerView();

            }
        }
    }
}
