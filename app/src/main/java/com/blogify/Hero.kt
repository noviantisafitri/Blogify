package com.blogify

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hero(
    val title: String,
    val author: String,
    val category: String,
    val description: String,
    val photo: Int,
    val date: String,
    val readingTime: String
) : Parcelable
