package com.example.mtgcollection;


import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import androidx.navigation.ui.NavigationUI;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.mtgcollection.data.Card;
import com.example.mtgcollection.data.RoomDB;
import com.example.mtgcollection.data.SharedPrefManager;
import com.example.mtgcollection.data.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    TextView name , username, allCards, eldritchMoon, aetherRevolt;

    ImageButton logOutBtn;
    User user = SharedPrefManager.getInstance(this).getUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);
        //Navigatiebalk
        BottomNavigationView navView = findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(this, R.id.navigationFragment);
        NavigationUI.setupWithNavController(navView, navController);
        logOutBtn = findViewById(R.id.logoutBtn);
        User user = SharedPrefManager.getInstance(this).getUser();
        String tokenId = SharedPrefManager.getInstance(this).getUser().getToken();
        CardRequest(tokenId);
        logOutBtn.setOnClickListener(v -> {
            finish();
            logOutRequest(user.getToken());
            SharedPrefManager.getInstance(getApplicationContext()).logout();
        });
    }
    public void logOutRequest(String Token) {
        StringRequest sr = new StringRequest(Request.Method.POST, URLs.URL_LOGOUT, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                String name = jsonObject.getString("message");
                Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
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
        MySingleton.getInstance(MainActivity.this).addToRequestQueue(sr);
    }
    public void CardRequest(String Token){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_CARDS, response -> {
            try {
                RoomDB db = RoomDB.getInstance(this);
                JSONArray jsonArray = new JSONArray(response);
                // for loop voor alle kaarten
                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject obj = jsonArray.getJSONObject(i);
                    int id = obj.getInt("id");
                    String name = obj.getString("name");
                    String generic_mana = obj.getString("generic_mana");
                    String type = obj.getString("type");
                    String type_name = obj.getString("type_name");
                    int power = obj.getInt("power");
                    int toughness = obj.getInt("toughness");
                    String image = obj.getString("image");
                    String set = obj.getString("set");
                    int inPossession = obj.getInt("inPossession");
                    Log.d("inPossession", String.valueOf(inPossession));
                    Card card = new Card(id, name,generic_mana,type, type_name ,power,toughness,image, set, inPossession , user.getEmail());
                    db.cardDao().insert(card);
                }
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
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}