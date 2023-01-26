package com.example.newsapp_v1.di

import com.example.newsapp_v1.data.paging.ArticlesDataSourceImpl
import com.example.newsapp_v1.data.remote.repository.NewsRepositoryImpl
import com.example.newsapp_v1.domain.repository.NewsPagingRepository
import com.example.newsapp_v1.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {


    @Binds
    @Singleton
    abstract fun bindNewsRepository(
        newsRepositoryImpl: NewsRepositoryImpl
    ): NewsRepository


    @Binds
    @Singleton
    abstract fun bindNewsPagingRepository(
        newsPagingRepositoryImpl: ArticlesDataSourceImpl
    ): NewsPagingRepository

}



