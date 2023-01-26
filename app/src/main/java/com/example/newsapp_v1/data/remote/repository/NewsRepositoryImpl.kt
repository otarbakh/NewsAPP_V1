package com.example.newsapp_v1.data.remote.repository


import com.example.newsapp_v1.common.util.Constants
import com.example.newsapp_v1.common.util.Resource
import com.example.newsapp_v1.data.local.db.ArticleDao
import com.example.newsapp_v1.data.local.models.toArticleDomain
import com.example.newsapp_v1.data.remote.models.toArticleDomain
import com.example.newsapp_v1.data.remote.services.NewsApi
import com.example.newsapp_v1.domain.models.ArticleDomain
import com.example.newsapp_v1.domain.models.toArticleEntity
import com.example.newsapp_v1.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val articleDao: ArticleDao
) : NewsRepository {

    override suspend fun upsertArticle(article: ArticleDomain) {
        return articleDao.upsert(article.toArticleEntity())

    }

    override suspend fun deleteArticle(article: ArticleDomain) {
        return articleDao.deleteArticle(article.toArticleEntity())

    }
}
