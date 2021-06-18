package com.example.mtgcollection.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.example.mtgcollection.MySingleton;
import com.example.mtgcollection.R;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText Email;
    EditText Password;
    String email;
    String password;
    Button loginBtn;
    Button registerBtn;
    String Login_URL = "http://10.0.2.2:8001/api/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Email = findViewById(R.id.emailField);
        Password = findViewById(R.id.passwordField);
        registerBtn = findViewById(R.id.registerBtn);
        loginBtn = findViewById(R.id.loginBtn);
        registerBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);

        password = Password.getText().toString();
        email = Email.getText().toString();
    }
    public void onClick(View v){

        if(v == registerBtn) {
            Intent toRegister = new Intent(this, RegisterActivity.class);
            startActivity(toRegister);
        }else if( v == loginBtn) {

        }
    }

}
