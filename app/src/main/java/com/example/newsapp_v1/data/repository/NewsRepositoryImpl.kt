package com.example.newsapp_v1.data.repository


import com.example.newsapp_v1.data.NewsApi
import com.example.newsapp_v1.data.model.Article
import com.example.newsapp_v1.data.model.NewsResponse
import com.example.newsapp_v1.domain.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi
) : NewsRepository {
    override suspend fun getBreakingNews(): List<Article> {
        val response = api.getBreakingNews(query = "autosport")
        return if (response.isSuccessful) {
            response.body()?.articles!!
        } else {
            emptyList()
        }
    }

    override suspend fun getSearchNews(searchQuery:String): List<Article> {
        val response = api.getSearchedNews(query = searchQuery)
        return if (response.isSuccessful) {
            response.body()?.articles!!
        } else {
            emptyList()
        }

    }
}
