package com.example.newsapp_v1.domain.repository


import com.example.newsapp_v1.domain.models.ArticleDomain
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun upsertArticle(article: ArticleDomain)
    suspend fun deleteArticle(article: ArticleDomain)
    suspend fun getArticle():Flow<List<ArticleDomain>>
}