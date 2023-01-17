package com.example.newsapp_v1.db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [ArticlesEntity::class], version = 1)

abstract class DataBase:RoomDatabase() {
    abstract val articleDao: ArticleDao

}