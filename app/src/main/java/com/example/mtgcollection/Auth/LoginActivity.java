package com.example.mtgcollection.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mtgcollection.MySingleton;
import com.example.mtgcollection.R;

import org.json.JSONArray;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText username;
    EditText password;
    Button loginBtn;
    Button registerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        registerBtn = findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);

    }
    public void onClick(View v){
        String LogninUrl = "http://10.0.2.2:8000/api/login";

        if(v == registerBtn) {
            Intent toRegister = new Intent(this, RegisterActivity.class);
            startActivity(toRegister);
        }else if( v == loginBtn) {

        }
    }

}
