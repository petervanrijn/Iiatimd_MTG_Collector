package com.example.mtgcollection;


import android.os.Bundle;
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
import com.example.mtgcollection.data.SharedPrefManager;
import com.example.mtgcollection.data.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    ImageButton logOutBtn;
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

}