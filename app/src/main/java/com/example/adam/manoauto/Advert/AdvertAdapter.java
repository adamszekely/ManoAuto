package com.example.adam.manoauto.Advert;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adam.manoauto.R;

import java.util.ArrayList;

/**
 * Created by Tomas on 05/11/2017.
 */

public class AdvertAdapter extends ArrayAdapter<AdvertActivity> {


   public AdvertAdapter(Activity context, ArrayList<AdvertActivity> adverts){
       super(context,0,adverts);
   }

    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.advert_list_row, parent, false);
        }

        AdvertActivity currentAdvert = getItem(position);

        ImageView image = (ImageView) listItemView.findViewById(R.id.image);
        image.setImageDrawable(currentAdvert.getImage());

        TextView title = (TextView) listItemView.findViewById(R.id.title);
        title.setText(currentAdvert.getTitle());

        TextView description = (TextView) listItemView.findViewById(R.id.description);
        description.setText(currentAdvert.getDescription());

        ImageButton favButton = (ImageButton)listItemView.findViewById(R.id.favouriteBtn);
        favButton.setImageResource(currentAdvert.getFavImage());

        TextView price = (TextView) listItemView.findViewById(R.id.price);
        price.setText(currentAdvert.getPrice());

        TextView year = (TextView) listItemView.findViewById(R.id.year);
        year.setText(currentAdvert.getYear());

        TextView gearBox = (TextView) listItemView.findViewById(R.id.gearBox);
        gearBox.setText(currentAdvert.getGearBox());

        TextView city = (TextView) listItemView.findViewById(R.id.city);
        city.setText(currentAdvert.getCity());

        return listItemView;
    }
}
