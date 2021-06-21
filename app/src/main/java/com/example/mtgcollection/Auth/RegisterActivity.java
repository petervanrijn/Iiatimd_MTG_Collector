package com.example.mtgcollection.Auth;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
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
import com.example.mtgcollection.data.SharedPrefManager;
import com.example.mtgcollection.data.User;
import com.google.gson.JsonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    Button register;
    ImageButton backBtn;
    EditText Username, Name, Email, Password, ConPassword;
    String sanctum_token;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
            return;
        }
//        buttons
        backBtn =  findViewById(R.id.backButton);
        register = findViewById(R.id.register);
//        Edit Textfields
        Username = findViewById(R.id.usernameField);
        Name =  findViewById(R.id.nameField);
        Email = findViewById(R.id.emailField);
        Password = findViewById(R.id.passwordField);
        ConPassword = findViewById(R.id.repeatPasswordField);
        builder = new AlertDialog.Builder(RegisterActivity.this);
        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if user pressed on button register
                //here we will register the user to server
                registerUser();
            }
        });
    }

    private void registerUser(){
        final String username = Username.getText().toString().trim();
        final String name = Name.getText().toString().trim();
        final String email = Email.getText().toString().trim();
        final String password = Password.getText().toString().trim();
        final String conPassword = ConPassword.getText().toString().trim();

        //first we will do the validations

        if (TextUtils.isEmpty(username)) {
            Username.setError("Please enter username");
            Username.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(email)) {
            Email.setError("Please enter your email");
            Email.requestFocus();
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Email.setError("Enter a valid email");
            Email.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Password.setError("Enter a password");
            Password.requestFocus();
            return;
        }
        if (!password.equals(conPassword)){
            ConPassword.setError("Enter the same password please.");
            ConPassword.requestFocus();
            return;
        } else{
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_REGISTER, response -> {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    sanctum_token = jsonObject.getString("token");
                        Toast.makeText(RegisterActivity.this, "Registration complete", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                        i.putExtra("TOKEN", sanctum_token);
                        finish();
                        startActivity(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }, error -> {
                    // error handler
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("name", name);
                    params.put("email", email);
                    params.put("username", username);
                    params.put("password", password);
                    params.put("password_confirmation", conPassword);
                    return params;
                }
            };
            MySingleton.getInstance(RegisterActivity.this).addToRequestQueue(stringRequest);
        }
    }
}

