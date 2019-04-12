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
import android.widget.LinearLayout;

import android.widget.TextView;
import android.widget.Toast;

import com.tecnatox.fishchoice.R;
import com.tecnatox.fishchoice.fish.Fish;
import com.tecnatox.fishchoice.fish.FishLibrary;

import java.util.ArrayList;


import static android.app.Activity.RESULT_OK;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<Fish> fishLibrary;
    private Context mContext;
    private FishLibrary fl;

    public RecyclerViewAdapter(ArrayList<Fish> fishLibrary, Context mContext) {
        this.fishLibrary = fishLibrary;
        this.mContext = mContext;
        this.fl = FishLibrary.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fish_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final int i = holder.getAdapterPosition();
        holder.fishName.setText(fishLibrary.get(i).getName());
        holder.dropdown.setVisibility(View.GONE);

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.input_qty.getText().toString().isEmpty() || Integer.parseInt(holder.input_qty.getText().toString()) <= 0){
                    holder.input_qty.setError("No value set");
                }else {
                    fl.addFishToUserLibrary(fishLibrary.get(i), Integer.parseInt(holder.input_qty.getText().toString()));

                    Intent resultIntent = new Intent();
//                    Fish f = fishLibrary.get(i);
//                    resultIntent.putExtra("fishItem", i);
//                    resultIntent.putExtra("quantity", Integer.parseInt(holder.input_qty.getText().toString()));
//
                    ((Activity)mContext).setResult(RESULT_OK, resultIntent);
                    ((Activity)mContext).finish();
                }
            }
        });

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, fishLibrary.get(i).getName(), Toast.LENGTH_SHORT).show();

                if (holder.dropdown.getVisibility() == View.VISIBLE){
                    holder.dropdown.setVisibility(View.GONE);
                }else{
                    holder.dropdown.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return fishLibrary.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        EditText input_qty;
        TextView fishName;
        LinearLayout parentLayout;
        LinearLayout dropdown;
        Button btnAdd;

        ViewHolder(View itemView) {
            super(itemView);
            fishName = itemView.findViewById(R.id.fish_item_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            dropdown = itemView.findViewById(R.id.dropdown);
            btnAdd = itemView.findViewById(R.id.add);
            input_qty = itemView.findViewById(R.id.input_quantity);

        }
    }
}
