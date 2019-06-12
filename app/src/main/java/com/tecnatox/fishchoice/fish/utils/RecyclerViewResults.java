package com.tecnatox.fishchoice.fish.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tecnatox.fishchoice.R;
import com.tecnatox.fishchoice.fish.Fish;
import com.tecnatox.fishchoice.fish.FishLibrary;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class RecyclerViewResults extends RecyclerView.Adapter<RecyclerViewResults.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<Float> totalNutris;
    private Context mContext;
    private FishLibrary fl;

    public RecyclerViewResults(ArrayList<Float> totalNutris, Context mContext) {
        this.totalNutris = totalNutris;
        this.mContext = mContext;
        this.fl = FishLibrary.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //TODO change layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.itemName.setText( fl.getNutrientsName(position));
        holder.pb.setMax(Math.round(fl.getNutrientsReq(position)*fl.u.getWeight()));
        holder.pb.setProgress(Math.round(totalNutris.get(position)));
        Float f1 = fl.getNutrientsReq(position);
        Float f2 = fl.u.getWeight();
        String aux = Math.round(totalNutris.get(position))+"/"+Math.round(fl.getNutrientsReq(position)*fl.u.getWeight())+" mg";
        holder.qtys.setText(aux);

    }

    @Override
    public int getItemCount() {
        return totalNutris.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        TextView itemName;
        ProgressBar pb;
        TextView qtys;

        ViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
            pb = itemView.findViewById(R.id.progressBar);
            qtys = itemView.findViewById(R.id.quantities);

        }
    }
}
