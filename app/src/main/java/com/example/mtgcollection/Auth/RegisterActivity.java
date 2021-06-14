package com.example.mtgcollection.Auth;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mtgcollection.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    Button register;
    ImageButton backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        backBtn =  findViewById(R.id.backButton);
        register = findViewById(R.id.register);
        backBtn.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == register){
            finishActivity(200);
        }else{
            finish();
        }
    }
}
