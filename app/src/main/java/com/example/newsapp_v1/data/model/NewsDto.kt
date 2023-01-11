package com.example.newsapp_v1.data.model

data class NewsDto(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)