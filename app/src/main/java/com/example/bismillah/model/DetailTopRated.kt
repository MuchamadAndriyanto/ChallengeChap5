package com.example.bismillah.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailTopRated(
    val imagepath: String,
    val title: String,
    val date: String,
    val overview: String

):Parcelable
