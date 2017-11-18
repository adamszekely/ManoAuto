package com.example.adam.manoauto.CreateAnnouncement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.adam.manoauto.R;
import com.example.adam.manoauto.Search.BrandlistActivity;

public class AddCarActivity extends AppCompatActivity {

    TextView chosenCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        chosenCar=(TextView) findViewById(R.id.chosenCarNameText);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences("PACKAGE", Context.MODE_PRIVATE);
        chosenCar.setText(prefs.getString("CarAdvert", ""));
    }

    public void chooseCarAddClick(View v)
    {
        Intent intent = new Intent(this, BrandlistActivity.class);
        intent.putExtra("FROMACTIVITY","Advert");
        startActivityForResult(intent, 1);
    }
}
