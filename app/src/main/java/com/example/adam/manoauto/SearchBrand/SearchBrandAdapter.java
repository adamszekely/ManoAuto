package com.example.adam.manoauto.SearchBrand;

/*import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adam.manoauto.Advert.AdvertActivity;
import com.example.adam.manoauto.R;

import java.util.ArrayList;*/

/**
 * Created by Adam on 08/11/2017.
 */

/*public class SearchBrandAdapter extends ArrayAdapter<SearchBrandActivity> {
    public SearchBrandAdapter(Activity context, ArrayList<SearchBrandActivity> brands){
        super(context,0,brands);
    }

    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.text_button_list_row, parent, false);
        }

        SearchBrandActivity current = getItem(position);

        ImageButton image = (ImageButton) listItemView.findViewById(R.id.buttonX);
        image.setImageResource(current.getImage());

        TextView title = (TextView) listItemView.findViewById(R.id.textViewOfBrand);
        title.setText(current.getText());

        return listItemView;
    }
}*/

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.adam.manoauto.R;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class SearchBrandAdapter extends RecyclerView.Adapter<SearchBrandAdapter.MyViewHolder> {
    private static final String TAG = "CustomAdapter";

    private ArrayList<SearchBrandActivity> mDataSet;
    private LayoutInflater inflater;


    public SearchBrandAdapter(ArrayList<SearchBrandActivity> brands, Context context) {
        mDataSet = brands;
        inflater = LayoutInflater.from(context);
    }

    // Create new views (invoked by the layout manager)


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");
        SearchBrandActivity temp = mDataSet.get(position);
        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.setData(temp,position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = inflater.inflate(R.layout.text_button_list_row, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(v);

        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageButton imageButton;
        TextView textView;

        SearchBrandActivity current;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textViewOfBrand);
            imageButton = (ImageButton) itemView.findViewById(R.id.buttonX);
        }

        public void setData(SearchBrandActivity current, int position) {
            this.imageButton.setImageResource(current.getImage());
            this.textView.setText(current.getText());
            this.current = current;
            ViewGroup.LayoutParams lp1=imageButton.getLayoutParams();
            ViewGroup.LayoutParams lp2=textView.getLayoutParams();
            if(lp1 instanceof FlexboxLayoutManager.LayoutParams && lp2 instanceof FlexboxLayoutManager.LayoutParams)
            {
                ((FlexboxLayoutManager.LayoutParams) lp1).setFlexGrow(1f);
                ((FlexboxLayoutManager.LayoutParams) lp2).setFlexGrow(1f);
            }
        }

    }
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

}



