package com.example.mtgcollection;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.mtgcollection.Auth.RegisterActivity;
import com.example.mtgcollection.data.SharedPrefManager;
import com.example.mtgcollection.data.User;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button getCard;
    ImageView cardImg;
    TextView cardName;
    Button logOutBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardName = findViewById(R.id.CardName);
        cardImg = findViewById(R.id.CardImg);
        logOutBtn = findViewById(R.id.logoutBtn);
        String tokenId = SharedPrefManager.getInstance(this).getUser().getToken();
        CardRequest(tokenId);
        User user = SharedPrefManager.getInstance(this).getUser();
        Log.d("token", String.valueOf(user));



        logOutBtn.setOnClickListener(v -> {
            finish();
            logOutRequest(tokenId);
            SharedPrefManager.getInstance(getApplicationContext()).logout();
        });

    }


    public void CardRequest(String Token){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_CARDS, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                String name = jsonObject.getString("name");
                String image = jsonObject.getString("image");
                Picasso.get().load(image).into(cardImg);
                cardName.setText(name);
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
        MySingleton.getInstance(MainActivity.this).addToRequestQueue(stringRequest);
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
