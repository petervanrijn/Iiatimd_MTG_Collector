package com.example.mtgcollection;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mtgcollection.data.Card;
import com.example.mtgcollection.data.RoomDB;

import com.squareup.picasso.Picasso;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    //Initialize variable
    private List<Card> dataList;
    private Activity context;
    private RoomDB database;


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
        //set text on text view
        holder.textView.setText(data.getName());
        //set image on image view
        Picasso.get().load(data.getImage()).into(holder.card);
        // hieronder moet code komen om te editen dat de kaart in je bezit is.

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Initialize variable
        TextView textView;
        ImageView card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.card_text_view);
            card = itemView.findViewById(R.id.card_image_view);
        }
    }
}
