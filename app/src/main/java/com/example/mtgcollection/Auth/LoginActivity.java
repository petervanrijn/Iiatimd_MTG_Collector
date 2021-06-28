package com.example.mtgcollection.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;

import com.android.volley.toolbox.StringRequest;

import com.example.mtgcollection.MainActivity;
import com.example.mtgcollection.MySingleton;
import com.example.mtgcollection.R;
import com.example.mtgcollection.URLs;
import com.example.mtgcollection.data.SharedPrefManager;
import com.example.mtgcollection.data.User;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText Email;
    EditText Password;
    Button loginBtn;
    Button registerBtn;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        progressBar = findViewById(R.id.progressBar);
        Email = findViewById(R.id.emailField);
        Password = findViewById(R.id.passwordField);
        registerBtn = findViewById(R.id.registerBtn);
        loginBtn = findViewById(R.id.loginBtn);
        registerBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
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
        final String password = Password.getText().toString().trim();
        final String email = Email.getText().toString().trim();
        //validating inputs
        if (TextUtils.isEmpty(email)) {
            Email.setError("Please enter your email");
            Email.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Password.setError("Please enter your password");
            Password.requestFocus();
            return;
        }
        // if everything is fine
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_LOGIN, response -> {
            progressBar.setVisibility(View.GONE);
            try {
                JSONObject obj = new JSONObject(response);
                if (obj.getString("code").equals("log_success")){
                    Toast.makeText(LoginActivity.this,"Login succes", Toast.LENGTH_SHORT).show();
                    //getting the user from the response
                    JSONObject userJson = obj.getJSONObject("user");
                    //create new user object
                    User user = new User(
                            userJson.getInt("id"),
                            userJson.getString("name"),
                            userJson.getString("email"),
                            userJson.getString("token")
                    );
                    //storing the user in shared preferences
                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);


                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    finish();
                    startActivity(i);
                }
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

