package com.example.newsapp_v1.domain

import com.example.newsapp_v1.data.model.Article
import com.example.newsapp_v1.data.model.NewsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface NewsRepository {

    suspend fun getBreakingNews(): Flow<List<Article>>

    suspend fun getSearchNews(searchQuery: String): Flow<List<Article>>
}