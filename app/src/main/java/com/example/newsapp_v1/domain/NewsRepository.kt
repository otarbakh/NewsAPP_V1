package com.example.newsapp_v1.domain

import com.example.newsapp_v1.common.util.Resource
import com.example.newsapp_v1.data.models.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getBreakingNews(): Flow<Resource<List<Article>>>

//    suspend fun getSearchNews(searchQuery: String): Flow<Resource<List<Article>>>
}