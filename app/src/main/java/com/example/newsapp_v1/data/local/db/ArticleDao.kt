package com.example.newsapp_v1.data.local.db

import androidx.room.*
import com.example.newsapp_v1.data.local.models.ArticlesEntity
import com.example.newsapp_v1.data.remote.models.Article
import kotlinx.coroutines.flow.Flow


@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: ArticlesEntity)

    @Query("SELECT * FROM articles")

    fun getAllArticles():Flow<List<ArticlesEntity>>

    @Delete
    suspend fun deleteArticle(article: ArticlesEntity)

}