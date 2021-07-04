package com.example.mtgcollection.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Database;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
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

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment {
    TextView name , username, allCards, eldritchMoon, aetherRevolt;
    RoomDB database;
    int aantal;
    public Profile() {
        // Required empty public constructor
    }

    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LayoutInflater lf = getActivity().getLayoutInflater();
        database = RoomDB.getInstance(getContext());
        View view =  lf.inflate(R.layout.profile_page, container, false);
        name = view.findViewById(R.id.my_name);
        username = view.findViewById(R.id.my_user);
        allCards = view.findViewById(R.id.all_cards_collected);
        eldritchMoon = view.findViewById(R.id.Eldritch_Moon_collected);
        aetherRevolt = view.findViewById(R.id.Aether_Revolt_collected);
        User user = SharedPrefManager.getInstance(getContext()).getUser();
        name.setText(user.getName());
        username.setText(user.getUsername());

        Log.d("user", String.valueOf(user.getName()));
        allCards.setText(database.cardDao().getAllCollected()+ "/392");
        eldritchMoon.setText(database.cardDao().getEldritchmoonCollected() + "/208");
        aetherRevolt.setText(database.cardDao().getAetherRevoltCollected() + "/184");

        return view;
    }


}