package com.mahmudul.newsapp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mahmudul.newsapp.R

fun ImageView.loadImageFromGlide(url: String?) {
    if(url!=null) {
        Glide.with(this)
            .load(url)
            .error(R.drawable.ic_launcher_background)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(this)
    }

}
