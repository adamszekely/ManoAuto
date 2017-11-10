package com.example.adam.manoauto;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adam.manoauto.SearchBrand.SearchBrandActivity;
import com.example.adam.manoauto.SearchBrand.SearchBrandAdapter;
import com.google.android.flexbox.AlignContent;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.AlignSelf;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.List;


public class SearchActivity extends AppCompatActivity implements ShareActionProvider.OnShareTargetSelectedListener, NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_content_activity_search);
        Toolbar toolbar= findViewById(R.id.toolBarSearch);
        toolbar.setTitle("");
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

        final ArrayList<SearchBrandActivity> brandList = new ArrayList<SearchBrandActivity>();
        brandList.add(new SearchBrandActivity(R.drawable.close_x_image,"BMW"));
        brandList.add(new SearchBrandActivity(R.drawable.close_x_image,"Mercedes"));
        brandList.add(new SearchBrandActivity(R.drawable.close_x_image,"Ford"));
        brandList.add(new SearchBrandActivity(R.drawable.close_x_image,"Ferrari"));
        brandList.add(new SearchBrandActivity(R.drawable.close_x_image,"BMW"));
        brandList.add(new SearchBrandActivity(R.drawable.close_x_image,"Mercedes"));
        brandList.add(new SearchBrandActivity(R.drawable.close_x_image,"Ford"));
        brandList.add(new SearchBrandActivity(R.drawable.close_x_image,"Ferrari"));
        brandList.add(new SearchBrandActivity(R.drawable.close_x_image,"BMW"));
        brandList.add(new SearchBrandActivity(R.drawable.close_x_image,"Mercedes"));
        brandList.add(new SearchBrandActivity(R.drawable.close_x_image,"Ford"));
        brandList.add(new SearchBrandActivity(R.drawable.close_x_image,"Ferrari"));


       SearchBrandAdapter brandAdapter = new SearchBrandAdapter(brandList,this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(brandAdapter);
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setAlignItems(AlignItems.FLEX_START);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);

        recyclerView.setLayoutManager(layoutManager);


       /* SearchBrandAdapter brandAdapter = new SearchBrandAdapter(this,brandList);
        ListView listView = (ListView)findViewById(R.id.listViewOfBrand);
        listView.setAdapter(brandAdapter);*/
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
        getMenuInflater().inflate(R.menu.reset_menu,menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.reset:
                // Reset all the fields

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void testClick(View v)
    {
        Toast.makeText(this,"Blblabla",Toast.LENGTH_SHORT).show();
    }
}
