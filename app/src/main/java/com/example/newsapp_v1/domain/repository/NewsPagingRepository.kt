package com.example.newsapp_v1.domain.repository

import androidx.paging.PagingData
import androidx.room.RoomDatabase
import com.example.newsapp_v1.data.local.db.ArticleDao
import com.example.newsapp_v1.domain.models.ArticleDomain
import kotlinx.coroutines.flow.Flow



interface NewsPagingRepository {
     fun getBreakingNews(q:String): Flow<PagingData<ArticleDomain>>
}