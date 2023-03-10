package com.example.newsapp_v1.data.remote.services

import com.example.newsapp_v1.data.remote.models.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getBreakingNews(
        @Query("q")
        query:String ,
        @Query("page")
        pageNumber : Int,
        @Query("pageSize")
        pageSize:Int,
        @Query("apiKey")
        apiKey:String
    ):Response<NewsResponse>
}