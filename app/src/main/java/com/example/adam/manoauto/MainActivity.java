package com.example.adam.manoauto;


import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
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
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adam.manoauto.Advert.Advert;
import com.example.adam.manoauto.Advert.AdvertActivity;
import com.example.adam.manoauto.Advert.AdvertAdapter;
import com.example.adam.manoauto.Advert.AdvertDetailedView;
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
    FirebaseAuth mAuth;
    TextView userName;
    ProgressBar downloadPercentage;
    View header;
    private FirebaseUser user;
    static boolean calledAlready = false;
    Query query;
    String key, firstKey, lastKey;
    ListView allCarsListView;

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
        downloadPercentage = (ProgressBar) findViewById(R.id.downloadPercentage);
        int colorCodeDark = Color.parseColor("#119f0e");
        downloadPercentage.getIndeterminateDrawable().setColorFilter(colorCodeDark, PorterDuff.Mode.SRC_IN);
        allCarsListView = (ListView) findViewById(R.id.listViewAllCars);
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

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                key = null;
            } else {
                key = extras.getString("keyOfLast");
            }
        } else {
            key = (String) savedInstanceState.getSerializable("keyOfLast");

        }


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final Query query2 = database.getReference().child("syncstate").orderByKey().limitToFirst(1);

        query2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    firstKey = dsp.getKey();
                    if (key == null) {
                        query = database.getReference().child("syncstate").startAt(firstKey).orderByKey().limitToFirst(6);
                    } else {
                        query = database.getReference().child("syncstate").startAt(key).orderByKey().limitToFirst(6);
                    }

                    query.addListenerForSingleValueEvent(new ValueEventListener() {
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
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
            case R.id.service:
                Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show();
                break;
                case R.id.favourites:
                Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show();
                break;
                case R.id.gasstations:
                Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show();
                break;
                case R.id.feedback:
                Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show();
                break;
                case R.id.settings:
                Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show();
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
        ArrayList<AdvertActivity> advertActivityArrayList = new ArrayList<AdvertActivity>();

        @Override
        protected Long doInBackground(DataSnapshot... dataSnapshots) {

            advertArrayList.clear();
            long times = 0;
            DataSnapshot myDataSnap = dataSnapshots[0];
            long count = myDataSnap.getChildrenCount();
            long i = 1;
            int k = 0;
            for (DataSnapshot dsp : myDataSnap.getChildren()) {

                Advert advert = dsp.getValue(Advert.class);
                advertArrayList.add(advert);
                byte[] decodedString = Base64.decode(advertArrayList.get(k).getImageURL1(), Base64.DEFAULT);
                Bitmap carImage1 = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);//Decode image, "thumbnail" is the object of image file
                Drawable drawable=new BitmapDrawable(getResources(),carImage1);
                advertActivityArrayList.add(new AdvertActivity(drawable,
                        advertArrayList.get(k).carName, advertArrayList.get(k).engine + "L, " + advertArrayList.get(k).carType,
                        R.drawable.starfavourites, "€"+advertArrayList.get(k).price, advertArrayList.get(k).year,
                        advertArrayList.get(k).gear, advertArrayList.get(k).fuel, advertArrayList.get(k).location,dsp.getKey()));
                if (count == i) {
                    lastKey = dsp.getKey();
                }
                i++;
                k++;
                //   publishProgress((int) (((i + 1) / (float) count) * 100));
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
            AdvertAdapter advertAdapter = new AdvertAdapter(MainActivity.this, advertActivityArrayList);
            allCarsListView.setAdapter(advertAdapter);
            downloadPercentage.setVisibility(View.GONE);
            allCarsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String key=advertActivityArrayList.get(position).getKey();
                    Intent intent=new Intent(MainActivity.this, AdvertDetailedView.class);
                    intent.putExtra("KEY",key);
                    startActivity(intent);
                }
            });
           /* AdvertRecycleViewAdapter advertAdapter = new AdvertRecycleViewAdapter(advertArrayList, context);
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listView);
            recyclerView.setAdapter(advertAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
            recyclerView.setLayoutManager(layoutManager);*/
            /*ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(advertAdapter);*/


        }
    }

    private void setProgressPercent(Integer[] values) {
        //  downloadPercentage.setText("Downloading: "+ values[0]+"%");
    }


    public void pageButtonClick(View v) {
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        intent.putExtra("keyOfLast", lastKey);
        startActivity(intent);
    }
}
