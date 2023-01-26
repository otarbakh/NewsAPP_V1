package com.example.newsapp_v1.domain.repository

import androidx.paging.PagingData
import com.example.newsapp_v1.domain.models.ArticleDomain
import kotlinx.coroutines.flow.Flow



interface NewsPagingRepository {
    suspend fun getBreakingNews(): Flow<PagingData<ArticleDomain>>

    suspend fun getSearchedNews(q:String): Flow<PagingData<ArticleDomain>>

    suspend fun getArticle(): Flow<PagingData<ArticleDomain>>

}