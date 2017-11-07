package com.example.adam.manoauto.Advert;

/**
 * Created by Tomas on 05/11/2017.
 */

public class AdvertActivity {

    private int image;
    private String title;
    private String description;
    private int favImage;

    private String price;
    private String year;
    private String gearBox;
    private String fuelType;
    private String city;

    public AdvertActivity(int image, String title, String description, int favImage, String price, String year, String gearBox, String fuelType, String city) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.favImage = favImage;
        this.price = price;
        this.year = year;
        this.gearBox = gearBox;
        this.fuelType = fuelType;
        this.city = city;
    }


    public int getImage() {
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
}
