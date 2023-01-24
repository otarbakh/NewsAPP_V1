package com.example.newsapp_v1.domain.models

import com.example.newsapp_v1.data.local.models.ArticlesEntity

data class ArticleDomain(
    val url:String,
    val description:String,
    val title:String,
    val urlToImage:String?

)


fun ArticleDomain.toArticleEntity():ArticlesEntity{
    return ArticlesEntity(
        url,urlToImage, title, description,

    )
}
