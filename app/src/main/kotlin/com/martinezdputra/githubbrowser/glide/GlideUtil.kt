package com.martinezdputra.githubbrowser.glide

import android.content.Context
import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.RequestBuilder

object GlideUtil {
    fun ImageView.loadSvgUrlWithGlide(context: Context, imageUrl: String) {
        val circularProgressDrawable = CircularProgressDrawable(this.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        val uri = Uri.parse(imageUrl)
        val requestBuilder: RequestBuilder<PictureDrawable> = GlideApp.with(context)
            .`as`(PictureDrawable::class.java)
            .placeholder(circularProgressDrawable)
            .listener(SvgSoftwareLayerSetter())

        requestBuilder.load(uri).into(this)
    }
}