package com.example.mtgcollection.Auth;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    Button register;
    ImageButton backBtn;
    EditText Username, Name, Email, Password, ConPassword;
    String username, name, email, password, conPassword;
    AlertDialog.Builder builder;
    // de URL moet 10.0.2.2 voor een request als de server op de pc staat
    String REG_URL = "http://10.0.2.2:8000/api/register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        buttons
        backBtn =  findViewById(R.id.backButton);
        register = findViewById(R.id.register);
//        Register fields
        Username = findViewById(R.id.usernameField);
        Name =  findViewById(R.id.nameField);
        Email = findViewById(R.id.emailField);
        Password = findViewById(R.id.passwordField);
        ConPassword = findViewById(R.id.repeatPasswordField);
        builder = new AlertDialog.Builder(RegisterActivity.this);

        backBtn.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        name = Name.getText().toString();
        email = Email.getText().toString();
        username = Username.getText().toString();
        password = Password.getText().toString();
        conPassword = ConPassword.getText().toString();
        if (v == register){
            if(name.equals("")||email.equals("")||username.equals("")||password.equals("")||conPassword.equals("")){
                builder.setTitle("Something went wrong!!!");
                builder.setMessage("You need to fill all fields.");
                builder.show();
            }else if(!(password.equals(conPassword))){
                builder.setTitle("Something went wrong!!!");
                builder.setMessage("Password is not the same.");
                displayAlert("ConPassword_error");
            }else{
                // vanaf diet doet de request het niet van weegen een error
                StringRequest stringRequest = new StringRequest(Request.Method.POST, REG_URL, response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String code = jsonObject.getString("code");
                        boolean token = jsonObject.has("token");
                        if (token) {
                            builder.setTitle("success!!!");
                            builder.setMessage("registration  in now complete");
                            displayAlert(code);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        displayAlert("reg_failed");
                    }
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
        }else{
            finish();
        }
    }



    private void displayAlert(final String code) {
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (code) {
                    case "ConPassword_error":
                        Password.setText("");
                        ConPassword.setText("");
                        break;
                    case "reg_success":
                        Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                        finish();
                        startActivity(i);
                        break;
                    case "reg_failed":
                        Name.setText("");
                        Email.setText("");
                        Username.setText("");
                        Password.setText("");
                        ConPassword.setText("");
                        break;
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
