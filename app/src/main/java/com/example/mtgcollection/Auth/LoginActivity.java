package com.example.mtgcollection.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mtgcollection.R;



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
    }

    public void onClick(View v){
        Intent toRegister = new Intent(this, RegisterActivity.class);
        startActivity(toRegister);

    }
}
