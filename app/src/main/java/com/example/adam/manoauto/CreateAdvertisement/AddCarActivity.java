package com.example.adam.manoauto.CreateAdvertisement;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.adam.manoauto.R;
import com.example.adam.manoauto.Search.BrandlistActivity;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AddCarActivity extends AppCompatActivity {

    TextView chosenCar;
    public static final int GET_FROM_GALLERY = 3;
    ImageButton mainImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        chosenCar=(TextView) findViewById(R.id.chosenCarNameText);
        mainImageButton=(ImageButton) findViewById(R.id.addXXLimgButton);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences("PACKAGE", Context.MODE_PRIVATE);
        chosenCar.setText(prefs.getString("CarAdvert", ""));
    }

    public void chooseCarAddClick(View v)
    {
        Intent intent = new Intent(this, BrandlistActivity.class);
        intent.putExtra("FROMACTIVITY","Advert");
        startActivityForResult(intent, 1);
    }

    public void mainImageClick(View v)
    {
        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //Detects request codes
        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                int nh = (int) ( bitmap.getHeight() * (512.0 / bitmap.getWidth()) );
                Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 512, nh, true);
                mainImageButton.setImageBitmap(scaled);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
