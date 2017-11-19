package com.example.adam.manoauto;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adam.manoauto.Advert.Advert;
import com.example.adam.manoauto.Advert.AdvertActivity;
import com.example.adam.manoauto.Advert.AdvertAdapter;
import com.example.adam.manoauto.CreateAdvertisement.AddCarActivity;
import com.example.adam.manoauto.Login.Login;
import com.example.adam.manoauto.Search.SearchActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ShareActionProvider.OnShareTargetSelectedListener, NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    private FirebaseAuth mAuth;
    TextView userName;
    View header;
    FirebaseUser user;
    final ArrayList<AdvertActivity> advertList = new ArrayList<AdvertActivity>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_content_activity_main);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        Toolbar toolbar = findViewById(R.id.toolBar);
        toolbar.setTitle("");

        setSupportActionBar(toolbar);

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
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child("syncstate");
        final ArrayList<Advert> advertArrayList = new ArrayList<Advert>();

// Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    Advert advert = dsp.getValue(Advert.class);
                    advertArrayList.add(advert);
                }
                for (int i = 0; i < advertArrayList.size(); i++) {
                    byte[] decodedString = Base64.decode(advertArrayList.get(i).imageURL1, Base64.DEFAULT);
                    BitmapFactory.Options options=new BitmapFactory.Options();// Create object of bitmapfactory's option method for further option use
                    options.inPurgeable = true; // inPurgeable is used to free up memory while required
                    Bitmap carImage1 = BitmapFactory.decodeByteArray(decodedString,0, decodedString.length,options);//Decode image, "thumbnail" is the object of image file
                    Bitmap carImage = Bitmap.createScaledBitmap(carImage1, 25 , 25 , true);// convert decoded bitmap into well scalled Bitmap format.
                    Drawable drawable = new BitmapDrawable(getResources(), carImage);
                   /* byte[] decodedString = Base64.decode(advertArrayList.get(i).imageURL1, Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    */
                    advertList.add(new AdvertActivity(drawable, advertArrayList.get(i).carName,
                            advertArrayList.get(i).engine + "cm3, " + advertArrayList.get(i).carType,
                            R.drawable.starfavourites,"€"+advertArrayList.get(i).price, advertArrayList.get(i).year, advertArrayList.get(i).gear,
                            advertArrayList.get(i).fuel, advertArrayList.get(i).location));
                }
                AdvertAdapter advertAdapter = new AdvertAdapter(MainActivity.this, advertList);
                ListView listView = (ListView) findViewById(R.id.listView);
                listView.setAdapter(advertAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        //        advertList.add(new AdvertActivity(getDrawable(advertArrayList.get(0).imageURL1),"Mercedes-Benz CLS350","2.5L, Coupe",R.drawable.starfavourites,"$22000","2002-06","Automatic","Petrol","Aarhus"));
        /*advertList.add(new AdvertActivity(R.drawable.mercedes,"Mercedes-Benz CLS350","2.5L, Coupe",R.drawable.starfavourites,"$22000","2002-06","Automatic","Petrol","Aarhus"));
        advertList.add(new AdvertActivity(R.drawable.mercedes,"Mercedes-Benz CLS350","2.5L, Coupe",R.drawable.starfavourites,"$22000","2002-06","Automatic","Petrol","Aarhus"));
        advertList.add(new AdvertActivity(R.drawable.mercedes,"Mercedes-Benz CLS350","2.5L, Coupe",R.drawable.starfavourites,"$22000","2002-06","Automatic","Petrol","Aarhus"));
        advertList.add(new AdvertActivity(R.drawable.mercedes,"Mercedes-Benz CLS350","2.5L, Coupe",R.drawable.starfavourites,"$22000","2002-06","Automatic","Petrol","Aarhus"));
        advertList.add(new AdvertActivity(R.drawable.mercedes,"Mercedes-Benz CLS350","2.5L, Coupe",R.drawable.starfavourites,"$22000","2002-06","Automatic","Petrol","Aarhus"));
        advertList.add(new AdvertActivity(R.drawable.mercedes,"Mercedes-Benz CLS350","2.5L, Coupe",R.drawable.starfavourites,"$22000","2002-06","Automatic","Petrol","Aarhus"));
        advertList.add(new AdvertActivity(R.drawable.mercedes,"Mercedes-Benz CLS350","2.5L, Coupe",R.drawable.starfavourites,"$22000","2002-06","Automatic","Petrol","Aarhus"));*/

       // if (!advertList.isEmpty()) {

       // }
    }

    @Override
    protected void onResume() {
        super.onResume();
        header = navigationView.getHeaderView(0);
        userName = (TextView) header.findViewById(R.id.usernameText);
        //gets the current user and displays him inside the app as the current logged in user
        userName.setText(user.getEmail());

    }

    //gives functionality to the items in the navigation drawer
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.myauto:
                Intent intent = new Intent(this, AddCarActivity.class);
                startActivity(intent);
                break;

            //"Logout" button
            case R.id.logout:
                mAuth.signOut();
                Intent signout = new Intent(this, Login.class);
                signout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(signout);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onShareTargetSelected(ShareActionProvider source, Intent intent) {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return true;
    }

    //gives functionality to the navigation bar buttons
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //"Edit Search" button
            case R.id.search_navigation:
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void searchClick(View v) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public Drawable getDrawable(String bitmapUrl) {
        try {
            URL url = new URL(bitmapUrl);
            Drawable d = new BitmapDrawable(getResources(), BitmapFactory.decodeStream(url.openConnection().getInputStream()));
            return d;
        } catch (Exception ex) {
            return null;
        }
    }
}
