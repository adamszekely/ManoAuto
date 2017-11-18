package com.example.adam.manoauto.Search;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adam.manoauto.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class BrandlistActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ListView brandListView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_content_activity_brand_list);
        brandListView=(ListView) findViewById(R.id.brandListView);
        Toolbar toolbar = findViewById(R.id.toolBarBrand);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.MainContentBrandList);

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

        brandListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
            {
                Intent intent = new Intent(BrandlistActivity.this, ModellistActivity.class);
                String message = (String)arg0.getItemAtPosition(position);;
                intent.putExtra("BRAND", message);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        setJSONFileArrayToListView();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {

            Intent intent = getIntent();
            intent.putExtra("Key", "true");
            setResult(RESULT_OK, intent);
        }
        return super.onKeyDown(keyCode, event);
    }

    public void setJSONFileArrayToListView() {
        ArrayList<String> names = new ArrayList<String>();

        try {

            JSONObject jsonObject = new JSONObject(getJSONFile(R.raw.car_list_short));
            JSONArray jsonArray = jsonObject.getJSONArray("brand");


            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject c = jsonArray.getJSONObject(i);
                names.add(c.getString("name"));

            }
            ArrayAdapter<String> nameAdapter = new ArrayAdapter<String>(this, android.R.layout.test_list_item, names){
                @Override
                public View getView(int position, View convertView, ViewGroup parent){
                    // Get the Item from ListView
                    View view = super.getView(position, convertView, parent);

                    // Initialize a TextView for ListView each Item
                    TextView tv = (TextView) view.findViewById(android.R.id.text1);

                    // Set the text color of TextView (ListView Item)
                    tv.setTextColor(Color.BLACK);
                    tv.setPadding(50,35,50,35);
                    tv.setTextSize(16);

                    // Generate ListView Item using TextView
                    return view;
                }
            };


            //Toast.makeText(this,names.get(5).toString(),Toast.LENGTH_LONG).show();
                    brandListView.setAdapter(nameAdapter);


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
