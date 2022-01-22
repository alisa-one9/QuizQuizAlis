package com.example.quizquizalis.ext

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.quizquizalis.R

fun View.visibility(isVisible: Boolean) {
    if (isVisible) this.visibility = View.VISIBLE
    else this.visibility = View.GONE
}

fun ImageView.loadImage(res: Boolean) {
    if (res)
        Glide.with(this)
            .load(R.drawable.ic_mark)
            .into(this)
    else
        Glide.with(this)
            .load(R.drawable.ic_baseline_close_24)
            .into(this)
}