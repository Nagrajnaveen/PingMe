package com.devnags.pingme.bindigAdapters;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class BindingAdapter {

    @androidx.databinding.BindingAdapter("app:loadImage")

    public static void addImage(ImageView view, String path){

        Picasso.get().load(path)
        .centerCrop()
        .into(view);

    }

}
