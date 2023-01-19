package com.example.newsapp_v1.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapp_v1.domain.models.ArticleDomain

@Entity(
    tableName = "articles"
)
data class ArticlesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val urlToImage: String,
    val title: String,
    val description: String,
    val url:String

)
 fun ArticlesEntity.toArticleDomain():ArticleDomain{
     return ArticleDomain(
         id, description, title, url, urlToImage
     )
 }