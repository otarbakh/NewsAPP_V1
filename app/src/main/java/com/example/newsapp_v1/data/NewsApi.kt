package com.example.newsapp_v1.data

import com.example.newsapp_v1.data.models.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getBreakingNews(
        @Query("q")
        query:String ,
//        @Query("page")
//        pageNumber : Int = 1,
        @Query("apiKey")
        apiKey:String
    ):Response<NewsResponse>

//    @GET("everything")
//    suspend fun getSearchedNews(
//        @Query("q")
//        query:String,
//        @Query("page")
//        pageNumber : Int = 1,
//        @Query("apiKey")
//        apiKey:String
//    ):Response<NewsResponse>
}