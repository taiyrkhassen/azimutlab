package com.example.azimutlab.helpers

import android.content.Context
import android.widget.Toast
import com.example.azimutlab.R
import com.example.azimutlab.custom_errors.NoInternetException
import java.lang.Exception

fun Context.toast(error: Exception) =
    if(error is NoInternetException) {
        Toast.makeText(this, this.getString(R.string.no_internet_toast), Toast.LENGTH_SHORT).show()
    } else {
        Toast.makeText(this, error.message.toString(), Toast.LENGTH_SHORT).show()
    }
