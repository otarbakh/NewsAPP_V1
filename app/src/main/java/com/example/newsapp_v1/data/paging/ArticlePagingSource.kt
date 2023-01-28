package com.example.newsapp_v1.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp_v1.common.util.Constants
import com.example.newsapp_v1.common.util.Constants.NETWORK_PAGE_SIZE
import com.example.newsapp_v1.common.util.Constants.STARTING_KEY
import com.example.newsapp_v1.data.local.models.toArticleDomain
import com.example.newsapp_v1.data.remote.models.toArticleDomain
import com.example.newsapp_v1.data.remote.services.NewsApi
import com.example.newsapp_v1.domain.models.ArticleDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import kotlin.math.max



class ArticlePagingSource(private val api: NewsApi,private val q:String) : PagingSource<Int, ArticleDomain>() {


    override fun getRefreshKey(state: PagingState<Int, ArticleDomain>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleDomain> {
        val page = params.key ?: STARTING_KEY

        return try {
            val response = api.getBreakingNews(q, params.loadSize, Constants.API_KEY)
            val articles = response.body()!!.articles.map { it.toArticleDomain() }

            val nextKey =
                if (articles.isEmpty()) {
                    null
                } else {
                    // By default, initial load size = 3 * NETWORK PAGE SIZE
                    // ensure we're not requesting duplicating items at the 2nd request
                    page + 1
                }

            val prevKey = if (
                page == STARTING_KEY) {
                null
            } else {
                page - 1
            }
            LoadResult.Page(
                data = articles,
                prevKey = prevKey,
                nextKey = nextKey
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}