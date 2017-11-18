package com.example.adam.manoauto.Search;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.adam.manoauto.CreateAnnouncement.AddCarActivity;
import com.example.adam.manoauto.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SearchActivity extends AppCompatActivity implements ShareActionProvider.OnShareTargetSelectedListener, NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    FlowLayout modelLayout;
    ArrayList<String> listCar;
    FirebaseAuth mAuth;
    TextView userName;
    List<String> helperListOfCars;
    SharedPreferences prefs;
    TextView textView;
    String temp, requiredValue;
    FlowLayout flowLayout;
    boolean stopped = false;
    LinearLayout linearLayoutYear, linearLayoutPrice,linearLayoutFuel,linearLayoutCarType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_content_activity_search);
        Toolbar toolbar = findViewById(R.id.toolBarSearch);
        flowLayout = (FlowLayout) findViewById(R.id.modelLayout);
        linearLayoutYear = (LinearLayout) findViewById(R.id.yearLayout);
        linearLayoutPrice = (LinearLayout) findViewById(R.id.priceLayout);
        linearLayoutFuel=(LinearLayout) findViewById(R.id.fuelLayout);
        linearLayoutCarType=(LinearLayout) findViewById(R.id.carTypeLayout);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        requiredValue = "false";
        modelLayout = (FlowLayout) findViewById(R.id.modelLayout);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayoutSearch);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close) {
            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        //Enable the drawer to open and close
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);


        listCar = new ArrayList<String>();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        View header = navigationView.getHeaderView(0);

        userName = (TextView) header.findViewById(R.id.usernameText);
        userName.setText(user.getEmail());

    }

    @Override
    protected void onResume() {
        super.onResume();
        prefs = getSharedPreferences("PACKAGE", Context.MODE_PRIVATE);
        String serialized = prefs.getString("Car", "");
        helperListOfCars = new ArrayList<String>(Arrays.asList(TextUtils.split(serialized, ",")));
        for (int i = 0; i < helperListOfCars.size(); i++) {
            String CurrentString = helperListOfCars.get(i);
            listCar.add(CurrentString);
        }

        if (requiredValue.equals("false") && stopped == false) {
            for (int i = 0; i < listCar.size(); i++) {
                getLayoutInflater().inflate(R.layout.text_button_list_row, modelLayout);
                TextView brandTextView = modelLayout.getChildAt(i).findViewById(R.id.textViewOfBrand);
                brandTextView.setText(listCar.get(i));
            }
            if (prefs.getInt("YEARFROM", -1) != -1 && prefs.getInt("YEARTO", -1) != -1) {
                getLayoutInflater().inflate(R.layout.text_button_list_row, linearLayoutYear);
                TextView yearTextView = linearLayoutYear.findViewById(R.id.textViewOfBrand);
                yearTextView.setText(prefs.getInt("YEARFROM", -1) + " - " + prefs.getInt("YEARTO", -1));
            }
            if (prefs.getInt("PRICEMIN", -1) != -1 && prefs.getInt("PRICEMAX", -1) != 1) {
                getLayoutInflater().inflate(R.layout.text_button_list_row, linearLayoutPrice);
                TextView priceTextView = linearLayoutPrice.findViewById(R.id.textViewOfBrand);
                priceTextView.setText(prefs.getInt("PRICEMIN", -1) + "€ - " + prefs.getInt("PRICEMAX", -1) + "€");
            }
            if (prefs.getString("FUELTYPE", "") != "") {
                getLayoutInflater().inflate(R.layout.text_button_list_row, linearLayoutFuel);
                TextView fuelTextView = linearLayoutFuel.findViewById(R.id.textViewOfBrand);
                fuelTextView.setText(prefs.getString("FUELTYPE", ""));
            }
            if (prefs.getString("CARTYPE", "") != "") {
                getLayoutInflater().inflate(R.layout.text_button_list_row, linearLayoutCarType);
                TextView fuelTextView = linearLayoutCarType.findViewById(R.id.textViewOfBrand);
                fuelTextView.setText(prefs.getString("CARTYPE", ""));
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopped = true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == 1 && resultCode == RESULT_OK) {

                requiredValue = data.getStringExtra("Key");
            }
        } catch (Exception ex) {
            Toast.makeText(this, ex.toString(),
                    Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onShareTargetSelected(ShareActionProvider source, Intent intent) {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reset_menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reset:
                flowLayout.removeAllViews();
                listCar.removeAll(listCar);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("Car", TextUtils.join(",", listCar));

                linearLayoutYear.removeAllViews();
                editor.putInt("YEARFROM", -1);
                editor.putInt("YEARTO", -1);

                linearLayoutPrice.removeAllViews();
                editor.putInt("PRICEMIN", -1);
                editor.putInt("PRICEMAX", -1);

                linearLayoutFuel.removeAllViews();
                editor.putString("FUELTYPE", "");

                linearLayoutCarType.removeAllViews();
                editor.putString("CARTYPE", "");
                editor.commit();


            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void deleteModel(View v) {
        ViewParent parent = v.getParent();
        LinearLayout linearLayout = null;


        if (parent instanceof LinearLayout) {
            // your button is inside a RelativeLayout
            linearLayout = (LinearLayout) parent;
        }
        SharedPreferences.Editor editor = prefs.edit();
        for (int i = 0; i < listCar.size(); i++) {
            textView = linearLayout.getChildAt(0).findViewById(R.id.textViewOfBrand);
            temp = textView.getText().toString().trim();
            if (temp.equals(listCar.get(i))) {
                flowLayout.removeView(linearLayout);
                listCar.remove(i);

                editor.putString("Car", TextUtils.join(",", listCar));

            }
        }
        textView = linearLayout.findViewById(R.id.textViewOfBrand);
        linearLayoutYear.removeView(linearLayout);
        editor.putInt("YEARFROM", -1);
        editor.putInt("YEARTO", -1);

        linearLayoutPrice.removeView(linearLayout);
        editor.putInt("PRICEMIN", -1);
        editor.putInt("PRICEMAX", -1);

        linearLayoutFuel.removeView(linearLayout);
        editor.putString("FUELTYPE", "");

        linearLayoutCarType.removeView(linearLayout);
        editor.putString("CARTYPE", "");
        editor.commit();
    }

    public void chooseBrandClick(View v) {
        Intent intent = new Intent(this, BrandlistActivity.class);
        intent.putExtra("FROMACTIVITY", "Search");
        startActivityForResult(intent, 1);
    }

    public void chooseYearsClick(View v) {
        Intent intent = new Intent(this, YearsActivity.class);
        startActivity(intent);
    }

    public void choosePriceClick(View v) {
        Intent intent = new Intent(this, PriceActivity.class);
        startActivity(intent);
    }

    public void searchCarClick(View v) {
       Toast.makeText(this,"BlaBla",Toast.LENGTH_LONG).show();
    }

    public void chooseFuelType(View v)
    {
        Intent intent = new Intent(this, FuelTypeActivity.class);
        startActivity(intent);
    }

    public void chooseCarType(View v)
    {
        Intent intent = new Intent(this, CarTypeActivity.class);
        startActivity(intent);
    }
}
