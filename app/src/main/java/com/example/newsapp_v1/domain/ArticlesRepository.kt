package com.example.newsapp_v1.domain

import com.example.newsapp_v1.data.local.models.ArticlesEntity
import kotlinx.coroutines.flow.Flow

interface ArticlesRepository {
    fun getArticle(): Flow<List<ArticlesEntity>>

    suspend fun upsertArticle(article: ArticlesEntity)

    suspend fun deleteArticle(article: ArticlesEntity)
}