package com.example.newsapp_v1.data.local.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapp_v1.data.local.models.ArticlesEntity


@Database(entities = [ArticlesEntity::class], version = 1)

abstract class ArticleDataBase: RoomDatabase() {
    abstract val articlesDao: ArticleDao

}