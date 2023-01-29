package com.example.newsapp_v1.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsapp_v1.common.util.Constants.NETWORK_PAGE_SIZE
import com.example.newsapp_v1.data.local.db.ArticleDao
import com.example.newsapp_v1.data.remote.services.NewsApi
import com.example.newsapp_v1.domain.models.ArticleDomain
import com.example.newsapp_v1.domain.repository.NewsPagingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton

class ArticlesDataSourceImpl @Inject constructor(
    private val newsApi: NewsApi,
):NewsPagingRepository {
    override  fun getBreakingNews(q:String): Flow<PagingData<ArticleDomain>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                ArticlePagingSource(newsApi,q)
            }
        ).flow
    }

}