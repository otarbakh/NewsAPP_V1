package com.example.newsapp_v1.data

import com.example.newsapp_v1.data.model.NewsResponse
import com.example.newsapp_v1.common.util.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/everything")
    suspend fun getBreakingNews(
        @Query("q")
        query:String ,
        @Query("page")
        pageNumber : Int = 1,
        @Query("apiKey")
        apiKey:String = API_KEY
    ):Response<NewsResponse>

    @GET("v2/everything")
    suspend fun getSearchedNews(
        @Query("q")
        query:String,
        @Query("page")
        pageNumber : Int = 1,
        @Query("apiKey")
        apiKey:String = API_KEY
    ):Response<NewsResponse>
}