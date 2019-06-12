package com.tecnatox.fishchoice.views.selectionFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tecnatox.fishchoice.R;
import com.tecnatox.fishchoice.fish.Fish;
import com.tecnatox.fishchoice.fish.FishLibrary;
import com.tecnatox.fishchoice.fish.utils.RecyclerViewAdapter;

import java.util.ArrayList;


public class AllSpecies extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_all_spicies, container, false);
        Context mContext = rootView.getContext();
        FishLibrary f = FishLibrary.getInstance();
        ArrayList<Fish> fishLibrary = new ArrayList<>(f.getLibrary());
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);
        f.asAdapter = new RecyclerViewAdapter(fishLibrary, mContext);
        recyclerView.setAdapter(f.asAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        return rootView;
    }

    public void search(String s){
        FishLibrary f = FishLibrary.getInstance();
        f.asAdapter.getFilter().filter(s);
    }


}
