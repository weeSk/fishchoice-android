package com.tecnatox.fishchoice.fish.utils;

import android.content.Context;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tecnatox.fishchoice.R;
import com.tecnatox.fishchoice.fish.FishLibrary;


public class RecyclerUserSelection extends RecyclerView.Adapter<RecyclerUserSelection.ViewHolder> {

    private static final String TAG = "RecyclerUserSelection";

    private Context mContext;
    private FishLibrary fl;

    public RecyclerUserSelection(Context mContext) {
        this.mContext = mContext;
        this.fl = FishLibrary.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fish_item_simple, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final int i = holder.getAdapterPosition();

        //TODO textedit constraints

        holder.fishName.setText(fl.userLibrary.get(i).getName());
        holder.qty.setText(String.valueOf(fl.userLibrary.get(i).getQuantity()));

        holder.qty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!holder.qty.getText().toString().isEmpty()) {
                    if (Integer.parseInt(holder.qty.getText().toString()) >= 0) {
                        fl.userLibrary.get(i).setQuantity(Integer.parseInt(holder.qty.getText().toString()));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        holder.button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                fl.userLibrary.remove(i);
                notifyItemRemoved(i);
                notifyItemRangeChanged(i, getItemCount());
                Toast.makeText(mContext, "Item deleted", Toast.LENGTH_SHORT).show();


                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return fl.userLibrary.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView fishName;
        EditText qty;
        LinearLayout parentLayout;
        LinearLayout button, container;

        ViewHolder(View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.fish_selection_container);
            fishName = itemView.findViewById(R.id.fish_item_name);
            qty = itemView.findViewById(R.id.qty);
            button = itemView.findViewById(R.id.parent_layout);
            container = itemView.findViewById(R.id.full_container);


        }
    }
}
