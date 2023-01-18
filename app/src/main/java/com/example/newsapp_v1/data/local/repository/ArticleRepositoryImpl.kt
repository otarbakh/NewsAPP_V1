package com.example.newsapp_v1.data.local.repository

import com.example.newsapp_v1.data.local.db.ArticleDao
import com.example.newsapp_v1.data.local.models.ArticlesEntity
import com.example.newsapp_v1.domain.ArticlesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val articleDao: ArticleDao
):ArticlesRepository {
    override fun getArticle(): Flow<List<ArticlesEntity>> {
        return articleDao.getAllArticles()

    }

    override suspend fun upsertArticle(article: ArticlesEntity) {
        return articleDao.upsert(article)

    }

    override suspend fun deleteArticle(article: ArticlesEntity) {
        return articleDao.deleteArticle(article)

    }
}