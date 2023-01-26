package com.example.newsapp_v1.data.paging


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp_v1.common.util.Constants.NETWORK_PAGE_SIZE
import com.example.newsapp_v1.common.util.Constants.STARTING_KEY
import com.example.newsapp_v1.data.local.db.ArticleDao
import com.example.newsapp_v1.data.local.models.toArticleDomain

import com.example.newsapp_v1.domain.models.ArticleDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import java.io.IOException


class SavedArticlePagingSource(private val dao: ArticleDao) : PagingSource<Int, ArticleDomain>() {


    override fun getRefreshKey(state: PagingState<Int, ArticleDomain>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleDomain> {
        val start = params.key ?: STARTING_KEY

        return try {
            withContext(Dispatchers.IO){
                val result = dao.getAllArticles().map {
                    it.toArticleDomain()
                }

                val nextKey =
                    if (result.isEmpty()) {
                        null
                    } else {
                        // By default, initial load size = 3 * NETWORK PAGE SIZE
                        // ensure we're not requesting duplicating items at the 2nd request
                        start + (params.loadSize / NETWORK_PAGE_SIZE)
                    }

                LoadResult.Page(
                    data = result,
                    prevKey = if (start == STARTING_KEY) null else start,
                    nextKey = nextKey
                )
            }

        } catch (io: IOException) {
            return LoadResult.Error(io)
        }


    }
}