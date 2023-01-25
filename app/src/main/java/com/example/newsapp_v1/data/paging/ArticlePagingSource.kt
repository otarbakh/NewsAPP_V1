package com.example.newsapp_v1.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp_v1.common.util.Constants
import com.example.newsapp_v1.common.util.Constants.STARTING_KEY
import com.example.newsapp_v1.common.util.Resource
import com.example.newsapp_v1.data.remote.models.toArticleDomain
import com.example.newsapp_v1.data.remote.services.NewsApi
import com.example.newsapp_v1.domain.models.ArticleDomain
import kotlinx.coroutines.flow.internal.NopCollector.emit
import retrofit2.HttpException
import java.io.IOException
import kotlin.math.max

class ArticlePagingSource(private val api: NewsApi):PagingSource<Int,ArticleDomain>() {



    override fun getRefreshKey(state: PagingState<Int, ArticleDomain>): Int? {

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleDomain> {
        val start = params.key ?: STARTING_KEY
        val range = start.until(start + params.loadSize)

        return try {
            val response = api.getBreakingNews("Autosport",start, Constants.API_KEY)

            val articles = response.body()!!.articles.map { it.toArticleDomain() }

        }catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }





    }

    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)


}