package com.example.newsapp_v1.db

import androidx.room.*
import com.example.newsapp_v1.data.model.Article
import kotlinx.coroutines.flow.Flow


@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)

    suspend fun upsert(article:Article):Long


    @Query("SELECT * FROM articles")
    fun getAllArticles():Flow<List<ArticlesEntity>>

    @Delete
    fun delete(article: ArticlesEntity)

}