package com.example.adam.manoauto.Advert;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adam.manoauto.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdvertDetailedView extends AppCompatActivity {

    String key;
    ImageView firstImage, smallImage1, smallImage2, smallImage3, smallImage4, smallImage5, smallImage6;
    TextView carName, price, years, km, engine, fuelType, carType, gearType, color, doorNo;
    TextView carState, wheelPosition, wheelDrive, wheelSize, carLocation, seatsNo, eSS, airCondition, iD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_car_advert);
        carName = (TextView) findViewById(R.id.showChosenCarNameText);
        firstImage = (ImageView) findViewById(R.id.showXXLimgButton);
        smallImage1 = (ImageView) findViewById(R.id.showSmallImg1);
        smallImage2 = (ImageView) findViewById(R.id.showSmallImg2);
        smallImage3 = (ImageView) findViewById(R.id.showSmallImg3);
        smallImage4 = (ImageView) findViewById(R.id.showSmallImg4);
        smallImage5 = (ImageView) findViewById(R.id.showSmallImg5);
        smallImage6 = (ImageView) findViewById(R.id.showSmallImg6);

        price = (TextView) findViewById(R.id.showTxtNewPrice);
        years = (TextView) findViewById(R.id.showTxtNewYear);
        km = (TextView) findViewById(R.id.showNewKm);
        engine = (TextView) findViewById(R.id.showTxtEngineSize);
        fuelType = (TextView) findViewById(R.id.showFuelType);
        carType = (TextView) findViewById(R.id.showTxtCarType);
        gearType = (TextView) findViewById(R.id.showTxtGearType);
        color = (TextView) findViewById(R.id.showTxtColor);
        doorNo = (TextView) findViewById(R.id.showTxtDoorNo);
        carState = (TextView) findViewById(R.id.showTxtCarState);
        wheelPosition = (TextView) findViewById(R.id.showTxtWheelPoistion);
        wheelDrive = (TextView) findViewById(R.id.showTxtWheelDrive);
        wheelSize = (TextView) findViewById(R.id.showTxtWheelSize);
        carLocation = (TextView) findViewById(R.id.showTxtCarLocation);
        seatsNo = (TextView) findViewById(R.id.showTxtSeatsNo);
        eSS = (TextView) findViewById(R.id.showTxtESS);
        airCondition = (TextView) findViewById(R.id.showTxtAirConditioning);
        iD = (TextView) findViewById(R.id.showTxtID);



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
                Advert advert = dataSnapshot.getValue(Advert.class);

                carName.setText(advert.carName);
                firstImage.setImageDrawable(retrieveImages(advert.getImageURL1()));
                smallImage1.setImageDrawable(retrieveImages(advert.getImageURL2()));
                smallImage2.setImageDrawable(retrieveImages(advert.getImageURL3()));
                smallImage3.setImageDrawable(retrieveImages(advert.getImageURL4()));
                smallImage4.setImageDrawable(retrieveImages(advert.getImageURL5()));
                smallImage5.setImageDrawable(retrieveImages(advert.getImageURL6()));
                smallImage6.setImageDrawable(retrieveImages(advert.getImageURL7()));

                price.setText(advert.price);
                years.setText(advert.year);
                km.setText(advert.km);
                engine.setText(advert.engine);
                fuelType.setText(advert.fuel);
                carType.setText(advert.carType);
                gearType.setText(advert.gear);
                color.setText(advert.color);
                doorNo.setText(advert.doorNo);
                carState.setText(advert.state);
                wheelPosition.setText(advert.steering);
                wheelDrive.setText(advert.wheelDrive);
                wheelSize.setText(advert.wheelSize);
                carLocation.setText(advert.location);
                seatsNo.setText(advert.doorNo);
                eSS.setText(advert.ess);
                airCondition.setText(advert.airCond);
                iD.setText(advert.id);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public Drawable retrieveImages(String image){
        byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
        // BitmapFactory.Options options = new BitmapFactory.Options();// Create object of bitmapfactory's option method for further option use
        // options.inPurgeable = true; // inPurgeable is used to free up memory while required
        Bitmap carImage1 = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);//Decode image, "thumbnail" is the object of image file
        // Bitmap carImage = Bitmap.createScaledBitmap(carImage1, 75, 75, true);// convert decoded bitmap into well scalled Bitmap format.
        Drawable drawable = new BitmapDrawable(getResources(), carImage1);
        return drawable;
    }

}
