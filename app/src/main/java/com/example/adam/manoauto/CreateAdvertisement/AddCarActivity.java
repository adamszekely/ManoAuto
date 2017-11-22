package com.example.adam.manoauto.CreateAdvertisement;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adam.manoauto.MainActivity;
import com.example.adam.manoauto.R;
import com.example.adam.manoauto.Search.BrandlistActivity;
import com.example.adam.manoauto.Search.CarTypeActivity;
import com.example.adam.manoauto.Search.FuelTypeActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddCarActivity extends AppCompatActivity {

    TextView chosenCar;

    public static final int GET_FROM_GALLERY_FIRST = 1;
    public static final int GET_FROM_GALLERY_SECOND = 2;
    public static final int GET_FROM_GALLERY_THIRD = 3;
    public static final int GET_FROM_GALLERY_FORTH = 4;
    public static final int GET_FROM_GALLERY_FIFTH = 5;
    public static final int GET_FROM_GALLERY_SIXTH = 6;
    public static final int GET_FROM_GALLERY_SEVENTH = 7;
    ImageButton mainImageButton, secondImageButton, thirdImageButton, forthImageButton, fifthImageButton, sixthImageButton, seventhImageButton;
    Button fuelTypeButton, carTypeButton;
    DatabaseReference ref;
    EditText priceEditText, yearEditText, kmEditText, engineSizeEditText, doorEditText, gearEditText, colorEditText, stateEditText,
            steeringEditText, essEditText, wheelEditText, locationEditText, seatEditText, wheelDriveEditText, airEditText, idEditText;
    SharedPreferences prefs;
    SharedPreferences.Editor edit;
    Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        prefs = getSharedPreferences("PACKAGE", Context.MODE_PRIVATE);
        edit = prefs.edit();
        chosenCar = (TextView) findViewById(R.id.chosenCarNameText);
        mainImageButton = (ImageButton) findViewById(R.id.addXXLimgButton);
        secondImageButton = (ImageButton) findViewById(R.id.addSmallImg1);
        thirdImageButton = (ImageButton) findViewById(R.id.addSmallImg2);
        forthImageButton = (ImageButton) findViewById(R.id.addSmallImg3);
        fifthImageButton = (ImageButton) findViewById(R.id.addSmallImg4);
        sixthImageButton = (ImageButton) findViewById(R.id.addSmallImg5);
        seventhImageButton = (ImageButton) findViewById(R.id.addSmallImg6);
        fuelTypeButton = (Button) findViewById(R.id.chooseFuelTypeButton);
        carTypeButton = (Button) findViewById(R.id.chooseCarTypeButton);
        priceEditText = (EditText) findViewById(R.id.showTxtNewPrice);
        yearEditText = (EditText) findViewById(R.id.showTxtNewYear);
        kmEditText = (EditText) findViewById(R.id.showNewKm);
        engineSizeEditText = (EditText) findViewById(R.id.showTxtEngineSize);
        doorEditText = (EditText) findViewById(R.id.showTxtDoorNo);
        gearEditText = (EditText) findViewById(R.id.showTxtGearType);
        colorEditText = (EditText) findViewById(R.id.showTxtColor);
        stateEditText = (EditText) findViewById(R.id.showTxtCarState);
        steeringEditText = (EditText) findViewById(R.id.showTxtWheelPoistion);
        wheelDriveEditText = (EditText) findViewById(R.id.showTxtESS);
        wheelEditText = (EditText) findViewById(R.id.showTxtWheelSize);
        locationEditText = (EditText) findViewById(R.id.showTxtCarLocation);
        seatEditText = (EditText) findViewById(R.id.showTxtSeatsNo);
        essEditText = (EditText) findViewById(R.id.showTxtESS);
        airEditText = (EditText) findViewById(R.id.showTxtAirConditioning);
        idEditText = (EditText) findViewById(R.id.showTxtID);

        Date timestamp =Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        String now=formatter.format(timestamp);
        ref = FirebaseDatabase.getInstance()
                .getReference(SyncStateContract.Constants.CONTENT_DIRECTORY)
                .child(now+FirebaseAuth.getInstance().getCurrentUser().getUid());



    }



    @Override
    protected void onResume() {
        super.onResume();

        chosenCar.setText(prefs.getString("CarAdvert", ""));
        if (prefs.getString("FIRSTIMAGEPATH", "") != "") {

            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(prefs.getString("FIRSTIMAGEPATH", "")));
                int nh = (int) (bitmap.getHeight() * (512.0 / bitmap.getWidth()));
                Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 512, nh, true);
                mainImageButton.setImageBitmap(scaled);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (prefs.getString("SECONDIMAGEPATH", "") != "") {

            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(prefs.getString("SECONDIMAGEPATH", "")));
                int nh = (int) (bitmap.getHeight() * (512.0 / bitmap.getWidth()));
                Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 512, nh, true);
                secondImageButton.setImageBitmap(scaled);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (prefs.getString("THIRDIMAGEPATH", "") != "") {

            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(prefs.getString("THIRDIMAGEPATH", "")));
                int nh = (int) (bitmap.getHeight() * (512.0 / bitmap.getWidth()));
                Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 512, nh, true);
                thirdImageButton.setImageBitmap(scaled);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (prefs.getString("FORTHIMAGEPATH", "") != "") {

            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(prefs.getString("FORTHIMAGEPATH", "")));
                int nh = (int) (bitmap.getHeight() * (512.0 / bitmap.getWidth()));
                Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 512, nh, true);
                forthImageButton.setImageBitmap(scaled);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (prefs.getString("FIFTHIMAGEPATH", "") != "") {

            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(prefs.getString("FIFTHIMAGEPATH", "")));
                int nh = (int) (bitmap.getHeight() * (512.0 / bitmap.getWidth()));
                Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 512, nh, true);
                fifthImageButton.setImageBitmap(scaled);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (prefs.getString("SIXTHIMAGEPATH", "") != "") {

            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(prefs.getString("SIXTHIMAGEPATH", "")));
                int nh = (int) (bitmap.getHeight() * (512.0 / bitmap.getWidth()));
                Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 512, nh, true);
                sixthImageButton.setImageBitmap(scaled);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (prefs.getString("SEVENTHIMAGEPATH", "") != "") {

            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(prefs.getString("SEVENTHIMAGEPATH", "")));
                int nh = (int) (bitmap.getHeight() * (512.0 / bitmap.getWidth()));
                Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 512, nh, true);
                seventhImageButton.setImageBitmap(scaled);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (prefs.getString("FUELTYPEADVERT", "Fuel Type") != "Fuel Type") {
            fuelTypeButton.setText(prefs.getString("FUELTYPEADVERT", "Fuel Type"));
        }
        if (prefs.getString("CARTYPEADVERT", "Car Type") != "Car Type") {
            carTypeButton.setText(prefs.getString("CARTYPEADVERT", "Car Type"));
        }
    }

    public void chooseCarAddClick(View v) {
        Intent intent = new Intent(this, BrandlistActivity.class);
        intent.putExtra("FROMACTIVITY", "Advert");
        startActivityForResult(intent, 1);
    }

    public void mainImageClick(View v) {
        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY_FIRST);
    }

    public void firstImageClick(View v) {
        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY_SECOND);
    }

    public void secondImageClick(View v) {
        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY_THIRD);
    }

    public void thirdImageClick(View v) {
        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY_FORTH);
    }

    public void forthImageClick(View v) {
        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY_FIFTH);
    }

    public void fifthImageClick(View v) {
        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY_SIXTH);
    }

    public void sixthImageClick(View v) {
        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY_SEVENTH);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri selectedImage = data.getData();
        //Detects request codes
        if (requestCode == GET_FROM_GALLERY_FIRST && resultCode == Activity.RESULT_OK) {
            putIntoSharedPreference("FIRSTIMAGEPATH", selectedImage.toString());

        } else if (requestCode == GET_FROM_GALLERY_SECOND && resultCode == Activity.RESULT_OK) {
            putIntoSharedPreference("SECONDIMAGEPATH", selectedImage.toString());

        } else if (requestCode == GET_FROM_GALLERY_THIRD && resultCode == Activity.RESULT_OK) {
            putIntoSharedPreference("THIRDIMAGEPATH", selectedImage.toString());

        } else if (requestCode == GET_FROM_GALLERY_FORTH && resultCode == Activity.RESULT_OK) {
            putIntoSharedPreference("FORTHIMAGEPATH", selectedImage.toString());

        } else if (requestCode == GET_FROM_GALLERY_FIFTH && resultCode == Activity.RESULT_OK) {
            putIntoSharedPreference("FIFTHIMAGEPATH", selectedImage.toString());

        } else if (requestCode == GET_FROM_GALLERY_SIXTH && resultCode == Activity.RESULT_OK) {
            putIntoSharedPreference("SIXTHIMAGEPATH", selectedImage.toString());

        } else if (requestCode == GET_FROM_GALLERY_SEVENTH && resultCode == Activity.RESULT_OK) {
            putIntoSharedPreference("SEVENTHIMAGEPATH", selectedImage.toString());
        }
    }

    public void chooseFuelTypeAdvert(View v) {
        Intent intent = new Intent(this, FuelTypeActivity.class);
        intent.putExtra("FROMACTIVITY", "Advert");
        startActivity(intent);
    }

    public void chooseCarTypeAdvert(View v) {
        Intent intent = new Intent(this, CarTypeActivity.class);
        intent.putExtra("FROMACTIVITY", "Advert");
        startActivity(intent);
    }

    public void saveAdvertClick(View v) {
        delay1();
        if (!chosenCar.getText().toString().equals("") && !priceEditText.getText().toString().equals("") &&
                !kmEditText.getText().toString().equals("") &&
                !engineSizeEditText.getText().toString().equals("") && !fuelTypeButton.getText().toString().equals("Fuel Type") &&
                !carTypeButton.getText().toString().equals("Car Type") && !doorEditText.getText().toString().equals("") &&
                !gearEditText.getText().toString().equals("") && !colorEditText.getText().toString().equals("") &&
                !stateEditText.getText().toString().equals("") && !steeringEditText.getText().toString().equals("") &&
                !wheelDriveEditText.getText().toString().equals("") && !locationEditText.getText().toString().equals("") &&
                !seatEditText.getText().toString().equals("") && !wheelEditText.getText().toString().equals("") &&
                !essEditText.getText().toString().equals("") && !airEditText.getText().toString().equals("") &&
                !idEditText.getText().toString().equals("") && !yearEditText.getText().toString().equals("")) {
            Bitmap mainImage = ((BitmapDrawable) mainImageButton.getDrawable()).getBitmap();
            encodeBitmapAndSaveToFirebase(mainImage, "imageURL1");
            Bitmap secondImage = ((BitmapDrawable) secondImageButton.getDrawable()).getBitmap();
            encodeBitmapAndSaveToFirebase(secondImage, "imageURL2");
            Bitmap thirdImage = ((BitmapDrawable) thirdImageButton.getDrawable()).getBitmap();
            encodeBitmapAndSaveToFirebase(thirdImage, "imageURL3");
            Bitmap forthImage = ((BitmapDrawable) forthImageButton.getDrawable()).getBitmap();
            encodeBitmapAndSaveToFirebase(forthImage, "imageURL4");
            Bitmap fifthImage = ((BitmapDrawable) fifthImageButton.getDrawable()).getBitmap();
            encodeBitmapAndSaveToFirebase(fifthImage, "imageURL5");
            Bitmap sixthImage = ((BitmapDrawable) sixthImageButton.getDrawable()).getBitmap();
            encodeBitmapAndSaveToFirebase(sixthImage, "imageURL6");
            Bitmap seventhImage = ((BitmapDrawable) seventhImageButton.getDrawable()).getBitmap();
            encodeBitmapAndSaveToFirebase(seventhImage, "imageURL7");
            ref.child("carName").setValue(chosenCar.getText().toString());
            ref.child("price").setValue(priceEditText.getText().toString());
            ref.child("km").setValue(kmEditText.getText().toString());
            ref.child("engine").setValue(engineSizeEditText.getText().toString());
            ref.child("fuel").setValue(fuelTypeButton.getText().toString());
            ref.child("carType").setValue(carTypeButton.getText().toString());
            ref.child("doorNo").setValue(doorEditText.getText().toString());
            ref.child("gear").setValue(gearEditText.getText().toString());
            ref.child("color").setValue(colorEditText.getText().toString());
            ref.child("state").setValue(stateEditText.getText().toString());
            ref.child("steering").setValue(steeringEditText.getText().toString());
            ref.child("wheelDrive").setValue(wheelDriveEditText.getText().toString());
            ref.child("wheelSize").setValue(wheelEditText.getText().toString());
            ref.child("location").setValue(locationEditText.getText().toString());
            ref.child("seat").setValue(seatEditText.getText().toString());
            ref.child("ess").setValue(essEditText.getText().toString());
            ref.child("airCond").setValue(airEditText.getText().toString());
            ref.child("id").setValue(idEditText.getText().toString());
            ref.child("year").setValue(yearEditText.getText().toString());
            deleteFromSharedPreference();
            edit.clear();

            delay();
        } else {
            Toast.makeText(this, "Please fill out all the fields", Toast.LENGTH_LONG).show();
        }
    }

    public void encodeBitmapAndSaveToFirebase(Bitmap bitmap, String name) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);

        String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
        ref.child(name).setValue(imageEncoded);
    }

    public void delay() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do  after 5s = 5000ms
                bitmap.recycle();
                Intent intent = new Intent(AddCarActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }, 5000);
    }

    public void delay1() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do  after 1s = 1000ms
                Toast.makeText(AddCarActivity.this, "Please wait", Toast.LENGTH_LONG).show();
            }
        }, 1000);
    }

    public void putIntoSharedPreference(String name, String path) {
        edit.putString(name, path);
        edit.commit();
    }

    public void deleteFromSharedPreference() {
        putIntoSharedPreference("FIRSTIMAGEPATH", "");
        putIntoSharedPreference("SECONDIMAGEPATH", "");
        putIntoSharedPreference("THIRDIMAGEPATH", "");
        putIntoSharedPreference("FORTHIMAGEPATH", "");
        putIntoSharedPreference("FIFTHIMAGEPATH", "");
        putIntoSharedPreference("SIXTHIMAGEPATH", "");
        putIntoSharedPreference("SEVENTHIMAGEPATH", "");
        putIntoSharedPreference("FUELTYPEADVERT", "Fuel Type");
        putIntoSharedPreference("CARTYPEADVERT", "Car Type");
        putIntoSharedPreference("CarAdvert", "");
    }
}
