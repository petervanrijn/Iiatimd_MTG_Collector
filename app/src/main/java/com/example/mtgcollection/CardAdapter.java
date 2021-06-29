package com.example.mtgcollection;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mtgcollection.data.Card;
import com.example.mtgcollection.data.RoomDB;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    //Initialize variable
    private List<Card> dataList;
    private Activity context;
    private RoomDB database;

    //createe constructor
    public CardAdapter(Activity context,List<Card> dataList){
        this.context = context;
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        //Initialise view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Initialize variable

        public ViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }
}
