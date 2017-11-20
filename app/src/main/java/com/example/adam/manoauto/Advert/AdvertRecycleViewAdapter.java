package com.example.adam.manoauto.Advert;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adam.manoauto.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by Adam on 20/11/2017.
 */

public class AdvertRecycleViewAdapter extends RecyclerView.Adapter<AdvertHolder> {

    List<Advert> list = Collections.emptyList();
    Context context;

    public AdvertRecycleViewAdapter(List<Advert> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public AdvertHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.advert_list_row, parent, false);
        AdvertHolder holder = new AdvertHolder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(AdvertHolder holder, int position) {

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        holder.title.setText(list.get(position).carName);
        holder.description.setText(list.get(position).engine+"cm3, "+list.get(position).carType);
        holder.city.setText(list.get(position).location);
        holder.favButton.setImageResource(R.drawable.starfavourites);

        byte[] decodedString = Base64.decode(list.get(position).imageURL1, Base64.DEFAULT);
        BitmapFactory.Options options = new BitmapFactory.Options();// Create object of bitmapfactory's option method for further option use
        options.inPurgeable = true; // inPurgeable is used to free up memory while required
        Bitmap carImage1 = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length, options);//Decode image, "thumbnail" is the object of image file
        Bitmap carImage = Bitmap.createScaledBitmap(carImage1, 75, 75, true);// convert decoded bitmap into well scalled Bitmap format.
        holder.image.setImageBitmap(carImage);
        carImage1.recycle();
        holder.gearBox.setText(list.get(position).gear);
        holder.price.setText("€"+list.get(position).price);
        holder.year.setText(list.get(position).year);
        holder.fuelType.setText(list.get(position).fuel);
        /*holder.title.setText(list.get(position).title);
        holder.description.setText(list.get(position).description);
        holder.imageView.setImageResource(list.get(position).imageId);*/

        //animate(holder);

    }

    @Override
    public int getItemCount() {
        //returns the number of elements the RecyclerView will display
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, Advert data) {
        list.add(position, data);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(Advert data) {
        int position = list.indexOf(data);
        list.remove(position);
        notifyItemRemoved(position);
    }

}
