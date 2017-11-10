package com.example.adam.manoauto.SearchBrand;


/**
 * Created by Adam on 08/11/2017.
 */

public class SearchBrandActivity {

    private int image;
    private String mText;

    public SearchBrandActivity(int image,String text)
    {
        this.image=image;
        this.mText=text;
    }

    public int getImage() {
        return image;
    }

    public String getText() {
        return mText;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setText(String text) {
        this.mText = text;
    }
}
