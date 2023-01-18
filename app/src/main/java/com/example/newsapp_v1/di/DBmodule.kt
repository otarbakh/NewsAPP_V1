package com.example.newsapp_v1.di

import android.content.Context
import androidx.room.Room
import com.example.newsapp_v1.data.local.db.ArticleDao
import com.example.newsapp_v1.data.local.db.ArticleDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DBmodule {

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): ArticleDataBase {
        return Room.databaseBuilder(
            context,
            ArticleDataBase::class.java,"articles_Db",
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideTasksDao(db: ArticleDataBase): ArticleDao {
        return db.articlesDao
    }
}