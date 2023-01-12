package com.example.newsapp_v1.domain

import com.example.newsapp_v1.data.model.Article
import com.example.newsapp_v1.data.model.NewsResponse

interface NewsRepository {

    suspend fun getBreakingNews(): List<Article>

    suspend fun getSearchNews(searchQuery:String): List<Article>
}