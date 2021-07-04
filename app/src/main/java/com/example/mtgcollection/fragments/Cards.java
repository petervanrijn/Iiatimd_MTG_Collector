package com.example.mtgcollection.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.mtgcollection.data.CardAdapter;
import com.example.mtgcollection.MySingleton;
import com.example.mtgcollection.R;
import com.example.mtgcollection.URLs;
import com.example.mtgcollection.data.Card;
import com.example.mtgcollection.data.RoomDB;
import com.example.mtgcollection.data.SharedPrefManager;
import com.example.mtgcollection.data.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cards extends Fragment {
    RecyclerView recyclerView;
    public Cards() {
        // Required empty public constructor
    }
    List<Card> cardData = new ArrayList<>();
    GridLayoutManager gridLayoutManager;
    RoomDB database;
    CardAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //intitialize database
        database = RoomDB.getInstance(getContext());
        //store database value in data list
        cardData = database.cardDao().getAll();

    }

    public void logOutRequest(String Token) {
        StringRequest sr = new StringRequest(Request.Method.POST, URLs.URL_LOGOUT, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                String name = jsonObject.getString("message");
                Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {

        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer " + Token);
                return params;
            }
        };
        MySingleton.getInstance(getActivity()).addToRequestQueue(sr);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.destination_cards, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        gridLayoutManager = new GridLayoutManager(getContext(), 2 ,GridLayoutManager.VERTICAL, false);
        adapter = new CardAdapter(getActivity(), cardData);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.hasFixedSize();
        recyclerView.getLayoutManager().removeAllViews();
        adapter.notifyDataSetChanged();
        return view;
    }

}