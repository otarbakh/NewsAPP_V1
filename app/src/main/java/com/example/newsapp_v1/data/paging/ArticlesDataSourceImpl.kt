package com.example.newsapp_v1.data.paging

import com.example.newsapp_v1.data.remote.services.NewsApi

class ArticlesDataSourceImpl(
    private val newsApi: NewsApi
):ArticlePagingSource {
}