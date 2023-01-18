package com.example.newsapp_v1.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "articles"
)
data class ArticlesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val urlToImage: String,
    val title: String,
    val description: String


)
