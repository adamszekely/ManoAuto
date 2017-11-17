package com.example.adam.manoauto.Search;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adam.manoauto.R;

public class YearsActivity extends AppCompatActivity {

    EditText yearFrom, yearTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_years);
        yearFrom = (EditText) findViewById(R.id.yearFrom);
        yearTo = (EditText) findViewById(R.id.yearTo);
    }

    public void yearsClick(View v) {

        if (!yearTo.getText().toString().equals("") && !yearFrom.getText().toString().equals("")) {

            if (Integer.parseInt(yearFrom.getText().toString()) <= Integer.parseInt(yearTo.getText().toString())) {
                Intent intent = new Intent(YearsActivity.this, SearchActivity.class);
                int from = Integer.parseInt(yearFrom.getText().toString());
                int to = Integer.parseInt(yearTo.getText().toString());
                SharedPreferences sharedPref = getSharedPreferences("PACKAGE", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("YEARFROM", from);
                editor.putInt("YEARTO", to);
                editor.commit();
                startActivity(intent);
            } else {
                Toast.makeText(this, "Number in 'from' has to be smaller than in 'to'", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Enter valid numbers", Toast.LENGTH_LONG).show();
        }
    }
}
