package com.example.newsapp_v1.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: ArticlesEntity):Long

    @Query("SELECT * FROM articles")

    fun getAllArticles():Flow<List<ArticlesEntity>>
}