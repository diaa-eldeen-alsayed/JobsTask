package com.example.jobstask.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.jobstask.R

object  BindingAdapters {

    @BindingAdapter("setImage")
    @JvmStatic
    fun  setImage(imageView: ImageView, imageUrl: String) {
        val circularProgressDrawable = CircularProgressDrawable(imageView.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        Glide.with(imageView.context)
                .load(imageUrl)
                .placeholder(circularProgressDrawable)
                .error(R.drawable.noimagefound)
                .into(imageView)
    }

}