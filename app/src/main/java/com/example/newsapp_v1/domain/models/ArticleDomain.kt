package com.example.newsapp_v1.domain.models

import com.example.newsapp_v1.data.local.models.ArticlesEntity

data class ArticleDomain(
    val id:Int?,
    val description:String,
    val title:String,
    val url:String,
    val urlToImage:String

)


fun ArticleDomain.toArticleEntity():ArticlesEntity{
    return ArticlesEntity(
        id, urlToImage, title, description, url

    )
}
