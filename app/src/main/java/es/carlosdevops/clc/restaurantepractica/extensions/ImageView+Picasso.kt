package es.carlosdevops.clc.restaurantepractica.extensions

import android.widget.ImageView
import com.squareup.picasso.Picasso
import java.net.URL

fun ImageView.loadImage(url: URL) {

    Picasso.with(context)
            .load(url.toString())
            .into(this)
}