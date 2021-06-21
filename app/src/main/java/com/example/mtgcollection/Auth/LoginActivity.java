package com.example.mtgcollection.Auth;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.example.mtgcollection.MainActivity;
import com.example.mtgcollection.MySingleton;
import com.example.mtgcollection.R;
import com.example.mtgcollection.URLs;
import com.google.gson.JsonObject;

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
    String sanctum_token;
    AlertDialog.Builder builder;
    String Login_URL = "http://10.0.2.2:8000/api/login";

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
        builder = new AlertDialog.Builder(LoginActivity.this);

    }
    public void onClick(View v){
        if(v == registerBtn) {
            Intent toRegister = new Intent(this, RegisterActivity.class);
            startActivity(toRegister);
        }else if( v == loginBtn) {
            LoginRequest();
        }
    }

    public void LoginRequest() {
        password = Password.getText().toString().trim();
        email = Email.getText().toString().trim();
        Log.d("input", email+ " " +password);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_LOGIN, response -> {
            try {
                JSONObject obj = new JSONObject(response);
                sanctum_token = obj.getString("token");
                Toast.makeText(LoginActivity.this,"Login succes", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                i.putExtra("TOKEN", sanctum_token);
                finish();
                startActivity(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        },
                error -> Toast.makeText(LoginActivity.this, "Wrong Email or Password", Toast.LENGTH_LONG).show()){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}

