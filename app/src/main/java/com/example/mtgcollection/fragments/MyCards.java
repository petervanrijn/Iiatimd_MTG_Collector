package com.example.mtgcollection.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mtgcollection.R;
import com.example.mtgcollection.data.Card;
import com.example.mtgcollection.data.CardAdapter;
import com.example.mtgcollection.data.RoomDB;
import com.example.mtgcollection.data.SharedPrefManager;
import com.example.mtgcollection.data.User;

import java.util.ArrayList;
import java.util.List;


public class MyCards extends Fragment {
    RecyclerView recyclerView;
    User user = SharedPrefManager.getInstance(getContext()).getUser();
    List<Card> cardData = new ArrayList<>();
    GridLayoutManager gridLayoutManager;
    CardAdapter adapter;
    RoomDB database;
    public MyCards() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            //intitialize database
            database = RoomDB.getInstance(getContext());
            //store database value in data list
            cardData = database.cardDao().getAllInpossessionCards(user.getEmail());
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.destination_mycards, container, false);
        recyclerView = view.findViewById(R.id.recyclerview2);
        gridLayoutManager = new GridLayoutManager(getContext(), 2 ,GridLayoutManager.VERTICAL, false);
        adapter = new CardAdapter(getActivity(), cardData);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.hasFixedSize();
        return view;
    }
}