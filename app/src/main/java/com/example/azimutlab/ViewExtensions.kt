package com.example.azimutlab

import android.view.View

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.isShow(): Boolean {
    return visibility == View.VISIBLE
}