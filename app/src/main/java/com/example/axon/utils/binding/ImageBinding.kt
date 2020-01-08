package com.example.axon.utils.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ImageBinding {
    companion object {
        @JvmStatic
        @BindingAdapter("image")
        fun loadImage(imageView: ImageView, url: String?) {
            url?.let {
                Glide.with(imageView.context)
                    .load(url)
                    .apply(RequestOptions().circleCrop())
                    .into(imageView)
            }
        }
    }
}