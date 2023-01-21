package com.example.newsapp_v1.domain.repository

import com.example.newsapp_v1.common.util.Resource
import com.example.newsapp_v1.data.local.models.ArticlesEntity
import com.example.newsapp_v1.data.remote.models.Article
import com.example.newsapp_v1.domain.models.ArticleDomain
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getBreakingNews(): Flow<Resource<List<ArticleDomain>>>

    suspend fun getArticle(): Flow<List<ArticleDomain>>


    suspend fun upsertArticle(article: ArticleDomain)


    suspend fun deleteArticle(article: ArticleDomain)


}