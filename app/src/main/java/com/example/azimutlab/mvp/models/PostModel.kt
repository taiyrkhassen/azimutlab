package com.example.azimutlab.mvp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class PostModel(
    @SerializedName("userId")
    val userId: Int,

    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("body")
    val body: String
)