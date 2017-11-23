package com.example.adam.manoauto.Search;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.adam.manoauto.CreateAdvertisement.AddCarActivity;
import com.example.adam.manoauto.R;

public class FuelTypeActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    String fromActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_type);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        Toolbar toolbar = findViewById(R.id.toolBarFuel);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                fromActivity = null;
            } else {
                fromActivity = extras.getString("FROMACTIVITY");
            }
        } else {
            fromActivity = (String) savedInstanceState.getSerializable("FROMACTIVITY");
        }
    }

    public void fuelTypeClick(View v) {
        if (fromActivity.equals("Search")) {
            Intent intent = new Intent(this, SearchActivity.class);
            int selectedFuelId = radioGroup.getCheckedRadioButtonId();
            if (selectedFuelId != -1) {
                View radiobutton = radioGroup.findViewById(selectedFuelId);
                int index = radioGroup.indexOfChild(radiobutton);
                RadioButton selectedFuel = (RadioButton) radioGroup.getChildAt(index);
                String fuelType = selectedFuel.getText().toString();
                SharedPreferences sharedPref = getSharedPreferences("PACKAGE", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("FUELTYPE", fuelType);
                editor.commit();
                startActivity(intent);
            } else {
                Toast.makeText(this, "Select a fuel type", Toast.LENGTH_LONG).show();
            }
        }
        else if(fromActivity.equals("Advert")){
            Intent intent = new Intent(this, AddCarActivity.class);
            int selectedFuelId = radioGroup.getCheckedRadioButtonId();
            if (selectedFuelId != -1) {
                View radiobutton = radioGroup.findViewById(selectedFuelId);
                int index = radioGroup.indexOfChild(radiobutton);
                RadioButton selectedFuel = (RadioButton) radioGroup.getChildAt(index);
                String fuelType = selectedFuel.getText().toString();
                SharedPreferences sharedPref = getSharedPreferences("PACKAGE", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("FUELTYPEADVERT", fuelType);
                editor.commit();
                startActivity(intent);
            } else {
                Toast.makeText(this, "Select a fuel type", Toast.LENGTH_LONG).show();
            }
        }
    }
}
