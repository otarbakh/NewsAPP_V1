package com.example.newsapp_v1.data.remote.repository


import com.example.newsapp_v1.common.util.Constants
import com.example.newsapp_v1.common.util.Resource
import com.example.newsapp_v1.data.local.db.ArticleDao
import com.example.newsapp_v1.data.local.models.toArticleDomain
import com.example.newsapp_v1.data.remote.models.ToArticleDomain
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
    private val api: NewsApi,
    private val articleDao: ArticleDao
) : NewsRepository {

    override suspend fun getArticle(): Flow<List<ArticleDomain>> {
        return articleDao.getAllArticles().map { it.map { it.toArticleDomain() } }

    }

    override suspend fun upsertArticle(article: ArticleDomain) {
        return articleDao.upsert(article.toArticleEntity())

    }

    override suspend fun deleteArticle(article: ArticleDomain) {
        return articleDao.deleteArticle(article.toArticleEntity())

    }

    override suspend fun getBreakingNews(): Flow<Resource<List<ArticleDomain>>> = flow  {

        try {
            emit(Resource.Loading(true))
            val response = api.getBreakingNews("Autosport", Constants.API_KEY)
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()!!.articles.map { it.ToArticleDomain() }))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "unexpected"))
        }
    }

    override suspend fun getSearchedNews(q:String): Flow<Resource<List<ArticleDomain>>> = flow  {

        try {
            emit(Resource.Loading(true))
            val response = api.getSearchedNews(q, Constants.API_KEY)
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()!!.articles.map { it.ToArticleDomain() }))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "unexpected"))
        }
    }

}
