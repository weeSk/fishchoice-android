package com.tecnatox.fishchoice.views.selectionFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tecnatox.fishchoice.R;
import com.tecnatox.fishchoice.fish.Fish;
import com.tecnatox.fishchoice.fish.FishLibrary;
import com.tecnatox.fishchoice.fish.utils.RecyclerViewAdapter;
import com.tecnatox.fishchoice.fish.utils.RecyclerViewResults;

import java.util.ArrayList;


public class NutrientsResultFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FishLibrary fl = FishLibrary.getInstance();
        ArrayList<Float> totalNutrients= new ArrayList<>(fl.calcNutris());
        View rootView = inflater.inflate(R.layout.fragment_nutrients_result, container, false);
        Context mContext = rootView.getContext();
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view_results);
        RecyclerViewResults mAdapter = new RecyclerViewResults(totalNutrients, mContext);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        return rootView;
    }



}
