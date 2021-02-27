package com.example.anchorbooks

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity (tableName = "books_table")
data class AnchorBooks(@SerializedName ("id")
                       @PrimaryKey   val id: Int,
                       @SerializedName("author")
                       val author: String,
                       @SerializedName("country")
                       val country: String,
                       @SerializedName("imageLink")
                       val imageLink: String,
                       @SerializedName("language")
                       val language: String,
                       @SerializedName("title")
                       val title: String)