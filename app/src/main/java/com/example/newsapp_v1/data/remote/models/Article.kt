package com.example.newsapp_v1.data.remote.models

import androidx.paging.PagingData
import com.example.newsapp_v1.domain.models.ArticleDomain
import com.example.newsapp_v1.domain.repository.NewsPagingRepository
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.flow.Flow

data class Article(
    @SerializedName("author")
    val author: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("source")
    val source: Source,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String?
)



fun Article.toArticleDomain(): ArticleDomain {

    return ArticleDomain(
        url,
        description,
        title,
        urlToImage,
        publishedAt,
        author
    )
}

