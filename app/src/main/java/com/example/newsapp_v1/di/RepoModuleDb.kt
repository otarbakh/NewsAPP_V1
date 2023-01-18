package com.example.newsapp_v1.di

import com.example.newsapp_v1.data.local.repository.ArticleRepositoryImpl
import com.example.newsapp_v1.domain.ArticlesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModuleDb {
    @Binds
    @Singleton
    abstract fun provideArticleRepository(repoImpl: ArticleRepositoryImpl): ArticlesRepository
}