package com.example.adam.manoauto.Advert;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adam.manoauto.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdvertDetailedView extends AppCompatActivity {

    String key;
    ImageView firstImage;
    TextView carName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_car_advert);
        carName=(TextView) findViewById(R.id.showChosenCarNameText);
        firstImage=(ImageView) findViewById(R.id.showXXLimgButton);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                key = null;

            } else {
                key = extras.getString("KEY");

            }
        } else {
            key = (String) savedInstanceState.getSerializable("KEY");

        }
        //Toast.makeText(this,key,Toast.LENGTH_LONG).show();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("syncstate").child(key);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               Advert advert=dataSnapshot.getValue(Advert.class);

                byte[] decodedString = Base64.decode(advert.getImageURL1(), Base64.DEFAULT);
               // BitmapFactory.Options options = new BitmapFactory.Options();// Create object of bitmapfactory's option method for further option use
               // options.inPurgeable = true; // inPurgeable is used to free up memory while required
                Bitmap carImage1 = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);//Decode image, "thumbnail" is the object of image file
               // Bitmap carImage = Bitmap.createScaledBitmap(carImage1, 75, 75, true);// convert decoded bitmap into well scalled Bitmap format.
                Drawable drawable=new BitmapDrawable(getResources(),carImage1);
               firstImage.setImageDrawable(drawable);
               carName.setText(advert.carName);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
