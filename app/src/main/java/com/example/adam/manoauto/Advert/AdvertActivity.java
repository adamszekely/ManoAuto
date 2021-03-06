package com.example.adam.manoauto.Advert;

import android.graphics.drawable.Drawable;

/**
 * Created by Tomas on 05/11/2017.
 */

public class AdvertActivity {

    private Drawable image;
    private String title;
    private String description;
    private int favImage;

    private String price;
    private String year;
    private String gearBox;
    private String fuelType;
    private String city;
    private String key;

    public AdvertActivity(Drawable image, String title, String description, int favImage, String price, String year, String gearBox, String fuelType, String city,String key) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.favImage = favImage;
        this.price = price;
        this.year = year;
        this.gearBox = gearBox;
        this.fuelType = fuelType;
        this.city = city;
        this.key=key;
    }


    public Drawable getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getFavImage() {
        return favImage;
    }

    public String getPrice() {
        return price;
    }

    public String getYear() {
        return year;
    }

    public String getGearBox() {
        return gearBox;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getCity() {
        return city;
    }

    public String getKey(){return key;}
}
