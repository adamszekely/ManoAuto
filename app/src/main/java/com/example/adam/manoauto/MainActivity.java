package com.example.adam.manoauto;


import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adam.manoauto.Advert.Advert;
import com.example.adam.manoauto.Advert.AdvertActivity;
import com.example.adam.manoauto.Advert.AdvertAdapter;
import com.example.adam.manoauto.Advert.AdvertHolder;
import com.example.adam.manoauto.Advert.AdvertRecycleViewAdapter;
import com.example.adam.manoauto.CreateAdvertisement.AddCarActivity;
import com.example.adam.manoauto.Login.Login;
import com.example.adam.manoauto.Search.SearchActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ShareActionProvider.OnShareTargetSelectedListener, NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    private FirebaseAuth mAuth;
    TextView userName;
    View header;
    FirebaseUser user;
    static boolean calledAlready = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_content_activity_main);
        if (!calledAlready) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(false);
            calledAlready = true;
        }
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

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
      //  final Query query = database.getReference().child("syncstate").limitToFirst(7);
      //  DatabaseReference ref = query.getRef();
        DatabaseReference ref = database.getReference().child("syncstate");



       //final DatabaseReference ref = database.getReference().child("syncstate");
       /* FirebaseRecyclerAdapter<Advert, ItemViewHolder> adapter = new FirebaseRecyclerAdapter<Advert, ItemViewHolder>(
                Advert.class, R.layout.advert_list_row, ItemViewHolder.class, query){

            protected void populateViewHolder(final ItemViewHolder viewHolder, Advert model, int position) {

                String key = this.getRef(position).getKey();
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        byte[] decodedString = Base64.decode(dataSnapshot.child("imageURL1").toString(), Base64.DEFAULT);
                        BitmapFactory.Options options = new BitmapFactory.Options();// Create object of bitmapfactory's option method for further option use
                        options.inPurgeable = true; // inPurgeable is used to free up memory while required
                        Bitmap carImage1 = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length, options);//Decode image, "thumbnail" is the object of image file
                        Bitmap carImage = Bitmap.createScaledBitmap(carImage1, 75, 75, true);// convert decoded bitmap into well scalled Bitmap format.
                        ImageView image = (ImageView) findViewById(R.id.image);
                        image.setImageBitmap(carImage);

                       String name = dataSnapshot.child("carName").getValue(String.class);
                        ((TextView)viewHolder.itemView.findViewById(android.R.id.title)).setText(name);


                        String descriptionS = dataSnapshot.child("engine").getValue(String.class)+"cm3, "+dataSnapshot.child("carType").getValue(String.class);
                        TextView description = (TextView) findViewById(R.id.description);
                        description.setText(descriptionS);

                        ImageButton favButton = (ImageButton) findViewById(R.id.favouriteBtn);
                        favButton.setImageResource(R.drawable.starfavourites);

                        String priceS = dataSnapshot.child("price").getValue(String.class);
                        TextView price = (TextView) findViewById(R.id.price);
                        price.setText(priceS);

                        String yearS = dataSnapshot.child("year").getValue(String.class);
                        TextView year = (TextView) findViewById(R.id.year);
                        year.setText(yearS);

                        String gearS = dataSnapshot.child("gear").getValue(String.class);
                        TextView gearBox = (TextView) findViewById(R.id.gearBox);
                        gearBox.setText(gearS);

                        String cityS = dataSnapshot.child("location").getValue(String.class);
                        TextView city = (TextView) findViewById(R.id.city);
                        city.setText(cityS);

                        String fuel = dataSnapshot.child("fuelType").getValue(String.class);
                        TextView fuelType = (TextView) findViewById(R.id.city);
                        city.setText(fuel);
                    }

                    public void onCancelled(DatabaseError firebaseError) { }
                });
            }
        };

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));*/


        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            //  int count = dataSnapshot.length;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                new DownloadFileTask().execute(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

// Attach a listener to read the data at our posts reference

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

    private class DownloadFileTask extends AsyncTask<DataSnapshot, Integer, Long> {
        Context context = getApplicationContext();
        final ArrayList<Advert> advertArrayList = new ArrayList<Advert>();

        /*  Query query = FirebaseDatabase.getInstance()
                  .getReference()
                  .child("syncstate")
                  .limitToLast(5);*/
        @Override
        protected Long doInBackground(DataSnapshot... dataSnapshots) {

            advertArrayList.clear();
            long times = 0;

            DataSnapshot myDataSnap = dataSnapshots[0];

            for (DataSnapshot dsp : myDataSnap.getChildren()) {

                Advert advert = dsp.getValue(Advert.class);
                advertArrayList.add(advert);
            }


            return times;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            setProgressPercent(values);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            AdvertRecycleViewAdapter advertAdapter = new AdvertRecycleViewAdapter(advertArrayList, context);
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listView);
            recyclerView.setAdapter(advertAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            /*ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(advertAdapter);*/


        }
    }

    private void setProgressPercent(Integer[] values) {
        // textView.setText("Downloading: "+ values[0]+"%");
    }
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public ItemViewHolder(View itemView) {
            super(itemView);
        }
    }

}
