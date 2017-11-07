package com.example.adam.manoauto.Register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.adam.manoauto.MainActivity;
import com.example.adam.manoauto.R;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }

    public void registerClick(View v)
    {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    }

