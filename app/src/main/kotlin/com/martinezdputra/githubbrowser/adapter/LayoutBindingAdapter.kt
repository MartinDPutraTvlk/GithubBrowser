package com.martinezdputra.githubbrowser.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.martinezdputra.githubbrowser.picasso.CircleTransform
import com.squareup.picasso.Picasso

@BindingAdapter("hideTextIfEmpty")
fun hideTextIfEmpty(view: TextView, text: String?) {
    view.text = text
    hideViewIfEmpty(view, text)
}

@BindingAdapter("hideViewIfEmpty")
fun hideViewIfEmpty(view: View, text: String?) {
    hideView(view, text.isNullOrEmpty())
}

@BindingAdapter("visibilityGone")
fun visibilityGone(view: View, condition: Boolean) {
    hideView(view, condition)
}

fun hideView(view: View, condition: Boolean) {
    if(condition) {
        view.visibility = View.GONE
    } else {
        view.visibility = View.VISIBLE
    }
}

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if(!url.isNullOrEmpty()) {
        Picasso.get()
            .load(url)
            .transform(CircleTransform())
            .into(view)
    }
    hideView(view, url.isNullOrEmpty())
}