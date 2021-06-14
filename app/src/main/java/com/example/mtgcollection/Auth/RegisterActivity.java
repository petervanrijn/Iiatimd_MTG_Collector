package com.example.mtgcollection.Auth;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.mtgcollection.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    Button register;
    ImageButton backBtn;
    EditText Username, Name, Email, Password, ConPassword;
    String username, name, email, password, conPassword;
    AlertDialog.Builder builder;
    String REG_URL = "http://127.0.0.1:8000/api/register";

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
                Log.d("reg" , "doet het");
                builder.setTitle("Something went wrong!!!");
                builder.setMessage("You need to fill all fields.");
                displayAlert("Input_error");
            }else if(!(password.equals(conPassword))){
                builder.setTitle("Something went wrong!!!");
                builder.setMessage("Password is not the same.");
                displayAlert("ConPassword_error");
            }else{
                StringRequest stringRequest = new StringRequest(Request.Method.POST, REG_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                finishActivity(200);
            }
        }else{
            finish();
        }
    }

    private void displayAlert(final String code) {

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (code.equals("Input_error")){
                    Password.setText("");
                    ConPassword.setText("");
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
