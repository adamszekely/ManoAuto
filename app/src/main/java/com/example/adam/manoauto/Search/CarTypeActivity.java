package com.example.adam.manoauto.Search;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.adam.manoauto.R;

public class CarTypeActivity extends AppCompatActivity {

    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_type);
        radioGroup=(RadioGroup) findViewById(R.id.radioGroupCarType);
    }
    public void carTypeClick(View v)
    {
        Intent intent = new Intent(this, SearchActivity.class);
        int selectedCarTypeId = radioGroup.getCheckedRadioButtonId();
        if (selectedCarTypeId != -1) {
            View radiobutton=radioGroup.findViewById(selectedCarTypeId);
            int index=radioGroup.indexOfChild(radiobutton);
            RadioButton selectedCar = (RadioButton) radioGroup.getChildAt(index);
            String carType = selectedCar.getText().toString();
            SharedPreferences sharedPref = getSharedPreferences("PACKAGE", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("CARTYPE", carType);
            editor.commit();
            startActivity(intent);
        } else {
            Toast.makeText(this, "Select a car type", Toast.LENGTH_LONG).show();
        }
    }
}
