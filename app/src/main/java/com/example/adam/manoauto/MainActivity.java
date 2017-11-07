package com.example.adam.manoauto;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.adam.manoauto.Advert.AdvertActivity;
import com.example.adam.manoauto.Advert.AdvertAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ShareActionProvider.OnShareTargetSelectedListener, NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_content);
        Toolbar toolbar= findViewById(R.id.toolBar);
        toolbar.setTitle(R.string.allcars);



        setSupportActionBar(toolbar);

        drawerLayout=(DrawerLayout) findViewById(R.id.drawerLayout2);

        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close){
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

        navigationView=(NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        //Enable the drawer to open and close
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);


        //ListView

        final ArrayList<AdvertActivity> advertList = new ArrayList<AdvertActivity>();
        advertList.add(new AdvertActivity(R.drawable.mercedes,"Mercedes-Benz CLS350","2.5L, Coupe",R.drawable.starfavourites,"$22000","2002-06","Automatic","Petrol","Aarhus"));
        advertList.add(new AdvertActivity(R.drawable.mercedes,"Mercedes-Benz CLS350","2.5L, Coupe",R.drawable.starfavourites,"$22000","2002-06","Automatic","Petrol","Aarhus"));
        advertList.add(new AdvertActivity(R.drawable.mercedes,"Mercedes-Benz CLS350","2.5L, Coupe",R.drawable.starfavourites,"$22000","2002-06","Automatic","Petrol","Aarhus"));
        advertList.add(new AdvertActivity(R.drawable.mercedes,"Mercedes-Benz CLS350","2.5L, Coupe",R.drawable.starfavourites,"$22000","2002-06","Automatic","Petrol","Aarhus"));
        advertList.add(new AdvertActivity(R.drawable.mercedes,"Mercedes-Benz CLS350","2.5L, Coupe",R.drawable.starfavourites,"$22000","2002-06","Automatic","Petrol","Aarhus"));
        advertList.add(new AdvertActivity(R.drawable.mercedes,"Mercedes-Benz CLS350","2.5L, Coupe",R.drawable.starfavourites,"$22000","2002-06","Automatic","Petrol","Aarhus"));
        advertList.add(new AdvertActivity(R.drawable.mercedes,"Mercedes-Benz CLS350","2.5L, Coupe",R.drawable.starfavourites,"$22000","2002-06","Automatic","Petrol","Aarhus"));
        advertList.add(new AdvertActivity(R.drawable.mercedes,"Mercedes-Benz CLS350","2.5L, Coupe",R.drawable.starfavourites,"$22000","2002-06","Automatic","Petrol","Aarhus"));


        AdvertAdapter advertAdapter = new AdvertAdapter(this,advertList);
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(advertAdapter);


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
}
