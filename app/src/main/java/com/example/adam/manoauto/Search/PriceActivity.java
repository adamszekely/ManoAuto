package com.example.adam.manoauto.Search;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adam.manoauto.R;

public class PriceActivity extends AppCompatActivity {

    EditText priceMin, priceMax;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price);
        priceMax=(EditText) findViewById(R.id.priceMax);
        priceMin=(EditText) findViewById(R.id.priceMin);

        Toolbar toolbar = findViewById(R.id.toolBarPrice);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    public void priceClick(View v) {

        if (!priceMin.getText().toString().equals("") && !priceMax.getText().toString().equals("")) {

            if (Integer.parseInt(priceMin.getText().toString()) <= Integer.parseInt(priceMax.getText().toString())) {
                Intent intent = new Intent(PriceActivity.this, SearchActivity.class);
                int min = Integer.parseInt(priceMin.getText().toString());
                int max = Integer.parseInt(priceMax.getText().toString());
                SharedPreferences sharedPref = getSharedPreferences("PACKAGE", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("PRICEMIN", min);
                editor.putInt("PRICEMAX", max);
                editor.commit();
                startActivity(intent);
            } else {
                Toast.makeText(this, "Number in 'Min price' has to be smaller than in 'Max price'", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Enter valid numbers", Toast.LENGTH_LONG).show();
        }
    }

}
