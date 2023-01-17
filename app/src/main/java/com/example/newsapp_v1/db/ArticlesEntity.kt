package com.example.newsapp_v1.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "articles"
)
data class ArticlesEntity(
    @PrimaryKey (autoGenerate = true)

    var id:Int? = null,

    val urlToImage: String,
    val title: String,
    val description: String


)
