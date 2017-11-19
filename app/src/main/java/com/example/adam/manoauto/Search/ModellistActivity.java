package com.example.adam.manoauto.Search;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adam.manoauto.CreateAdvertisement.AddCarActivity;
import com.example.adam.manoauto.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModellistActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ListView modelListView;
    String name, fromActivity;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    SharedPreferences sharedPreferences;
    List<String> helperListOfCars, listCar;
    boolean added = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_content_activity_model_list);
        listCar = new ArrayList<String>();
        modelListView = (ListView) findViewById(R.id.modelListView);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                name = null;
                fromActivity = null;
            } else {
                name = extras.getString("BRAND");
                fromActivity = extras.getString("FROMACTIVITY");
            }
        } else {
            name = (String) savedInstanceState.getSerializable("BRAND");
            fromActivity = (String) savedInstanceState.getSerializable("FROMACTIVITY");
        }

        Toolbar toolbar = findViewById(R.id.toolBarBrand);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.MainContentModelList);

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
        // Toast.makeText(this,name,Toast.LENGTH_LONG).show();

        helperListOfCars = new ArrayList<String>();

        modelListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                String message = (String) arg0.getItemAtPosition(position);
                if (fromActivity.equals("Search")) {
                    Intent intent = new Intent(ModellistActivity.this, SearchActivity.class);
                    //getting the list from shared preference to add to it
                    sharedPreferences = getSharedPreferences("PACKAGE", Context.MODE_PRIVATE);

                    String serialized = sharedPreferences.getString("Car", "DefValue");
                    helperListOfCars = new ArrayList<String>(Arrays.asList(TextUtils.split(serialized, ",")));

                    for (int i = 0; i < helperListOfCars.size(); i++) {
                        String CurrentString = helperListOfCars.get(i);
                        listCar.add(CurrentString);
                    }

                    if (!helperListOfCars.contains(name + " " + message)) {
                        listCar.add(name + " " + message);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Car", TextUtils.join(",", listCar));
                        editor.commit();
                        startActivity(intent);
                    } else {
                        Toast.makeText(ModellistActivity.this, "This model is already selected", Toast.LENGTH_LONG).show();
                        listCar.removeAll(listCar);
                    }
                } else if (fromActivity.equals("Advert")) {
                    Intent intent = new Intent(ModellistActivity.this, AddCarActivity.class);
                    //getting the list from shared preference to add to it
                    sharedPreferences = getSharedPreferences("PACKAGE", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("CarAdvert", name + " " + message);
                    editor.commit();
                    startActivity(intent);
                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        setJSONFileArrayToListView();
    }

    public void setJSONFileArrayToListView() {

        ArrayList<String> modelList = new ArrayList<String>();

        try {

            JSONObject jsonObject = new JSONObject(getJSONFile(R.raw.car_list_short));
            JSONArray jsonArray = jsonObject.getJSONArray("brand");


            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject c = jsonArray.getJSONObject(i);

                if (c.getString("name").equals(name)) {

                    for (int k = 0; k < c.getJSONArray("model").length(); k++) {
                        modelList.add(c.getJSONArray("model").getString(k));
                    }
                }
            }
            ArrayAdapter<String> nameAdapter = new ArrayAdapter<String>(this, android.R.layout.test_list_item, modelList) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    // Get the Item from ListView
                    View view = super.getView(position, convertView, parent);

                    // Initialize a TextView for ListView each Item
                    TextView tv = (TextView) view.findViewById(android.R.id.text1);

                    // Set the text color of TextView (ListView Item)
                    tv.setTextColor(Color.BLACK);
                    tv.setPadding(50, 35, 50, 35);
                    tv.setTextSize(16);

                    // Generate ListView Item using TextView
                    return view;
                }
            };


            modelListView.setAdapter(nameAdapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getJSONFile(int input) {
        String json = null;
        try {
            InputStream is = getResources().openRawResource(input);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
