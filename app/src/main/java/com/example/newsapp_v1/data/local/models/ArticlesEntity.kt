package com.example.newsapp_v1.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapp_v1.domain.models.ArticleDomain

@Entity(
    tableName = "articles"
)
data class ArticlesEntity(
    @PrimaryKey(autoGenerate = false)
    val url: String,
    val urlToImage: String?,
    val title: String?,
    val description: String?,
    val publishedAt:String?,
    val author:String?


    )

fun ArticlesEntity.toArticleDomain(): ArticleDomain {
    return ArticleDomain(
        url, description, title, urlToImage,publishedAt,author
    )
}