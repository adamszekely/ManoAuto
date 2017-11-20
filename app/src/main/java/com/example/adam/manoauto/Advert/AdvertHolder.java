package com.example.adam.manoauto.Advert;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adam.manoauto.R;

/**
 * Created by Adam on 20/11/2017.
 */

public class AdvertHolder extends RecyclerView.ViewHolder {
    TextView title,description,price,year,gearBox,city,fuelType;
    ImageButton favButton;
    ImageView image;
    AdvertHolder(View itemView) {
        super(itemView);

         image = (ImageView) itemView.findViewById(R.id.image);


         title = (TextView) itemView.findViewById(R.id.title);


         description = (TextView) itemView.findViewById(R.id.description);


         favButton = (ImageButton)itemView.findViewById(R.id.favouriteBtn);


         price = (TextView) itemView.findViewById(R.id.price);


         year = (TextView) itemView.findViewById(R.id.year);


         gearBox = (TextView) itemView.findViewById(R.id.gearBox);


         city = (TextView) itemView.findViewById(R.id.city);

         fuelType=(TextView) itemView.findViewById(R.id.fuelType);

    }
    public void setTitle(TextView title) {
        this.title = title;
    }

    public void setDescription(TextView description) {
        this.description = description;
    }

    public void setPrice(TextView price) {
        this.price = price;
    }

    public void setYear(TextView year) {
        this.year = year;
    }

    public void setGearBox(TextView gearBox) {
        this.gearBox = gearBox;
    }

    public void setCity(TextView city) {
        this.city = city;
    }

    public void setFuelType(TextView fuelType) {
        this.fuelType = fuelType;
    }

    public void setFavButton(ImageButton favButton) {
        this.favButton = favButton;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
    public TextView getTitle() {
        return title;
    }

    public TextView getDescription() {
        return description;
    }

    public TextView getPrice() {
        return price;
    }

    public TextView getYear() {
        return year;
    }

    public TextView getGearBox() {
        return gearBox;
    }

    public TextView getCity() {
        return city;
    }

    public TextView getFuelType() {
        return fuelType;
    }

    public ImageButton getFavButton() {
        return favButton;
    }

    public ImageView getImage() {
        return image;
    }
}
