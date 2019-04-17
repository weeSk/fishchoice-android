package com.tecnatox.fishchoice.views.selectionFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tecnatox.fishchoice.R;
import com.tecnatox.fishchoice.fish.Fish;
import com.tecnatox.fishchoice.fish.FishLibrary;
import com.tecnatox.fishchoice.fish.utils.RecyclerViewAdapter;

import java.util.ArrayList;


public class FrequentSpices extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_frequent_spicies, container, false);
        Context mContext = rootView.getContext();

        TextView tv = rootView.findViewById(R.id.tv_empty_freq);

        FishLibrary f = FishLibrary.getInstance();
        ArrayList<Fish> fishLibrary = f.frequents;

        if (!fishLibrary.isEmpty()){
            tv.setVisibility(View.GONE);
        }

        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(fishLibrary, mContext);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        return rootView;
    }


}
