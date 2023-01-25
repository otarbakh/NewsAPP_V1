package com.example.newsapp_v1.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp_v1.common.util.Constants
import com.example.newsapp_v1.common.util.Constants.NETWORK_PAGE_SIZE
import com.example.newsapp_v1.common.util.Constants.STARTING_KEY
import com.example.newsapp_v1.data.remote.models.toArticleDomain
import com.example.newsapp_v1.data.remote.services.NewsApi
import com.example.newsapp_v1.domain.models.ArticleDomain
import retrofit2.HttpException
import java.io.IOException
import kotlin.math.max

class ArticlePagingSource(private val api: NewsApi):PagingSource<Int,ArticleDomain>() {



    override fun getRefreshKey(state: PagingState<Int, ArticleDomain>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleDomain> {
        val start = params.key ?: STARTING_KEY
        val range = start.until(start + params.loadSize)

        return try {
            val response = api.getBreakingNews("Autosport",start, Constants.API_KEY)

            val articles = response.body()!!.articles.map { it.toArticleDomain() }

            val nextKey =
                if (articles.isEmpty()) {
                    null
                } else {
                    // By default, initial load size = 3 * NETWORK PAGE SIZE
                    // ensure we're not requesting duplicating items at the 2nd request
                    start + (params.loadSize / NETWORK_PAGE_SIZE)
                }

            LoadResult.Page(
                data = articles,
                prevKey = if (start == STARTING_KEY) null else start,
                nextKey = nextKey
            )

        }catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }





    }

    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)


}