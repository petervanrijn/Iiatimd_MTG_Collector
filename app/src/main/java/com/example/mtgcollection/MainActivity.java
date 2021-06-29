package com.example.mtgcollection;


import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    ImageView cardImg;
    TextView cardName;
    Button logOutBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Navigatiebalk
        BottomNavigationView navView = findViewById(R.id.navigation_bottom);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_account, R.id.navigation_friends, R.id.navigation_cards, R.id.navigation_trade )
                .build();

//        NavController navController = Navigation.findNavController(this, R.id.navHostFragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);


//        cardName = findViewById(R.id.CardName);
//        cardImg = findViewById(R.id.CardImg);
//        logOutBtn = findViewById(R.id.logoutBtn);
//        String tokenId = SharedPrefManager.getInstance(this).getUser().getToken();
//        CardRequest(tokenId);
//        User user = SharedPrefManager.getInstance(this).getUser();
//        Log.d("token", String.valueOf(user));



//        logOutBtn.setOnClickListener(v -> {
//            finish();
//            logOutRequest(tokenId);
//            SharedPrefManager.getInstance(getApplicationContext()).logout();
//        });

    }

//
//    public void CardRequest(String Token){
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_CARDS, response -> {
//            try {
//                JSONArray jsonArray = new JSONArray(response);
//                JSONObject jsonObject = jsonArray.getJSONObject(0);
//                String name = jsonObject.getString("name");
//                String image = jsonObject.getString("image");
//                Picasso.get().load(image).into(cardImg);
//                cardName.setText(name);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }, error -> {
//
//        }) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("Authorization", "Bearer " + Token);
//                return params;
//            }
//        };
//        MySingleton.getInstance(MainActivity.this).addToRequestQueue(stringRequest);
//    }
//
//    public void logOutRequest(String Token) {
//        StringRequest sr = new StringRequest(Request.Method.POST, URLs.URL_LOGOUT, response -> {
//            try {
//                JSONObject jsonObject = new JSONObject(response);
//                String name = jsonObject.getString("message");
//                Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }, error -> {
//
//        }) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("Authorization", "Bearer " + Token);
//                return params;
//            }
//        };
//        MySingleton.getInstance(MainActivity.this).addToRequestQueue(sr);
//    }
}
