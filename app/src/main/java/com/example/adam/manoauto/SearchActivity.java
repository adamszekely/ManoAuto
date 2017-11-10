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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.flexbox.AlignContent;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.AlignSelf;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nex3z.flowlayout.FlowLayout;

import java.net.Authenticator;
import java.util.ArrayList;
import java.util.List;


public class SearchActivity extends AppCompatActivity implements ShareActionProvider.OnShareTargetSelectedListener, NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    FlowLayout modelLayout;
    ArrayList<String> listCar;
    FirebaseAuth mAuth;
    TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_content_activity_search);
        Toolbar toolbar = findViewById(R.id.toolBarSearch);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        modelLayout = (FlowLayout) findViewById(R.id.modelLayout);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout2);

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
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.button_view);



        listCar=new ArrayList<String>();
        listCar.add("BMW");
        listCar.add("Mercedes");
        listCar.add("Ferrari");
        listCar.add("VW");
        listCar.add("Ford");
        listCar.add("Audi");
        listCar.add("Lada");
        listCar.add("Honda");
        listCar.add("Trabant");
        listCar.add("Renault");

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user=  mAuth.getCurrentUser();
        View header = navigationView.getHeaderView(0);

        userName=(TextView) header.findViewById(R.id.usernameText) ;
        userName.setText(user.getEmail());

    }

    @Override
    protected void onResume() {
        super.onResume();
        for (int i = 0; i < listCar.size(); i++) {
            getLayoutInflater().inflate(R.layout.text_button_list_row, modelLayout);
            TextView brandTextView = modelLayout.getChildAt(i).findViewById(R.id.textViewOfBrand);
            brandTextView.setText(listCar.get(i));

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
                // Reset all the fields

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void testClick(View v) {
        Toast.makeText(this, "Blblabla", Toast.LENGTH_SHORT).show();
    }

    public void deleteModel(View v)
    {
        ViewParent parent = v.getParent();
        LinearLayout linearLayout=null;
        FlowLayout flowLayout=(FlowLayout) findViewById(R.id.modelLayout);
        TextView textView;
        String temp;
        if (parent instanceof LinearLayout) {
            // your button is inside a RelativeLayout
            linearLayout = (LinearLayout) parent;

        }

        for (int i = 0; i < listCar.size(); i++) {
           textView= linearLayout.getChildAt(0).findViewById(R.id.textViewOfBrand);
           temp=textView.getText().toString().trim();
           if(temp.equals(listCar.get(i)))
           {
               flowLayout.removeView(linearLayout);
               listCar.remove(i);
           }
        }
    }
}
