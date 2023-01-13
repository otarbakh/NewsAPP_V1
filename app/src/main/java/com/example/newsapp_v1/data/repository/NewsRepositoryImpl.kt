package com.example.newsapp_v1.data.repository


import com.example.newsapp_v1.common.util.Resource
import com.example.newsapp_v1.data.NewsApi
import com.example.newsapp_v1.data.model.Article
import com.example.newsapp_v1.domain.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi
) : NewsRepository {

    override suspend fun getBreakingNews(): Flow<List<Article>> = flow  {

        try {
            emit(Resource.Loading(true))
            val response = api.getBreakingNews(query = "autosport")
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()!!))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "unexpected"))
        }
    }


    override suspend fun getSearchNews(searchQuery:String): Flow<List<Article>> = flow {

        try {
            emit(Resource.Loading(true))
            val response = api.getSearchedNews(query = searchQuery)
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()!!.articles))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "unexpected"))
        }

    }
}
