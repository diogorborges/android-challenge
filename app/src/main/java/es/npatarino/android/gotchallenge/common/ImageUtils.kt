package es.npatarino.android.gotchallenge.common

import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import es.npatarino.android.gotchallenge.R

fun setThumbnailImage(imageView: ImageView, path: String?) {
    GlideApp.with(imageView)
        .load(path)
        .apply(RequestOptions().circleCrop())
        .apply(RequestOptions().placeholder(R.drawable.ic_image_placeholder))
        .into(imageView)
}
