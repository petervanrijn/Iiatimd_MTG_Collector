package com.example.mtgcollection.data;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mtgcollection.R;

import com.squareup.picasso.Picasso;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    //Initialize variable
    private List<Card> dataList;
    private Activity context;
    private RoomDB database;
    private String clickedItem = "";
    private String oldClickedItem = "";


    //create constructor

    public CardAdapter(Activity context,List<Card> dataList){
        this.context = context;
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        //Initialise view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        //initialze main database
        Card data = dataList.get(position);
        //intialize database
        database = RoomDB.getInstance(context);
        //set image on image view
        Picasso.get().load(data.getImage()).into(holder.card);
        // checks if card is possession
        if ( data.getInPossession() == 1){
            holder.cardFrame.setBackgroundResource(R.drawable.possession_frame);
        }
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedItem = data.getName();
                Log.d("clicked", String.valueOf(clickedItem));


                //update the database to in possession 1 and the styling
                if (!clickedItem.equals(oldClickedItem)){
                    oldClickedItem = clickedItem;
                    database.cardDao().setInPossession(data.getId());
                    holder.cardFrame.setBackgroundResource(R.drawable.possession_frame);
                }else if(clickedItem.equals(oldClickedItem) ) {
                    oldClickedItem = "";
                    database.cardDao().setNotInPossession(data.getId());
                    holder.cardFrame.setBackgroundResource(R.drawable.frame);
                }
                Log.d("itemclick", String.valueOf(data.getInPossession()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Initialize variable
        ImageView card;
        TextView cardFrame;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card_image_view);
            cardFrame = itemView.findViewById(R.id.card_image_frame);

        }
    }
}
