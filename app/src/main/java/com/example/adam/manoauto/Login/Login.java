package com.example.adam.manoauto.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.adam.manoauto.MainActivity;
import com.example.adam.manoauto.R;
import com.example.adam.manoauto.Register.Register;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void loginClick(View v)
    {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void registerClick(View v)
    {
        Intent intent=new Intent(this, Register.class);
        startActivity(intent);
    }
}
