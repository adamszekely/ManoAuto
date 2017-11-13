package com.example.adam.manoauto;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nex3z.flowlayout.FlowLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ModellistActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    ListView modelListView;
    String name;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_content_activity_model_list);
        modelListView=(ListView) findViewById(R.id.modelListView) ;

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                name= null;
            } else {
                name= extras.getString("EXTRA_MESSAGE");
            }
        } else {
            name= (String) savedInstanceState.getSerializable("EXTRA_MESSAGE");
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

                if(c.getString("name").equals(name)) {

                    for (int k = 0; k < c.getJSONArray("model").length(); k++) {
                        modelList.add(c.getJSONArray("model").getString(k));
                    }
                }
            }
            ArrayAdapter<String> nameAdapter = new ArrayAdapter<String>(this, android.R.layout.test_list_item, modelList){
                @Override
                public View getView(int position, View convertView, ViewGroup parent){
                    // Get the Item from ListView
                    View view = super.getView(position, convertView, parent);

                    // Initialize a TextView for ListView each Item
                    TextView tv = (TextView) view.findViewById(android.R.id.text1);

                    // Set the text color of TextView (ListView Item)
                    tv.setTextColor(Color.BLACK);

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
