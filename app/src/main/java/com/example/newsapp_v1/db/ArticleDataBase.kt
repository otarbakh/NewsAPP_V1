package com.example.newsapp_v1.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [ArticlesEntity::class], version = 1)

@TypeConverters(Converters::class)

abstract class ArticleDataBase : RoomDatabase() {

    abstract fun getArticleDao():ArticleDao

    companion object{

        @Volatile
        private var instance:ArticleDataBase? = null
        private val LOCK = Any()


        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also{ instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDataBase::class.java,
                "article_db.db"
            ).build()
    }

}