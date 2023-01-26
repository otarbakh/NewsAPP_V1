package com.example.newsapp_v1.ui.viewmodels


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newsapp_v1.common.util.Resource
import com.example.newsapp_v1.data.local.models.ArticlesEntity
import com.example.newsapp_v1.domain.models.ArticleDomain
import com.example.newsapp_v1.domain.repository.NewsPagingRepository
import com.example.newsapp_v1.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepositoryImpl: NewsRepository,
    private val newsPagingRepository: NewsPagingRepository

) : ViewModel() {

    private var currentSavedResult:Flow<PagingData<ArticleDomain>>? = null
    private var currentBreakingResult:Flow<PagingData<ArticleDomain>>? = null
    private var currentSearchedresult:Flow<PagingData<ArticleDomain>>? = null

    private val _newsState = MutableStateFlow<PagingData<ArticleDomain>>(PagingData.empty())
    val newsState = _newsState.asStateFlow()

    private val _searchState = MutableStateFlow<PagingData<ArticleDomain>>(PagingData.empty())
    val searchState = _searchState.asStateFlow()



     fun getSearchedNews(q:String):Flow<PagingData<ArticleDomain>>{
        val searchedResult:Flow<PagingData<ArticleDomain>> =
            newsPagingRepository.getSearchedNews(q).cachedIn(viewModelScope)
        Log.d("Otar",searchedResult.toString())
        currentSearchedresult = searchedResult
        return searchedResult
    }
     suspend fun getBreakingNewsFromViewModel(): Flow<PagingData<ArticleDomain>> {
        val result:Flow<PagingData<ArticleDomain>> =
            newsPagingRepository.getBreakingNews().cachedIn(viewModelScope)
         currentBreakingResult = result
         return result
    }

    suspend fun getSavedArticle():Flow<PagingData<ArticleDomain>>{
        val result:Flow<PagingData<ArticleDomain>> =
            newsPagingRepository.getArticle().cachedIn(viewModelScope)
        currentSavedResult = result
        return result
    }

    fun upsertArticle(article: ArticleDomain) {
        CoroutineScope(Dispatchers.IO).launch {
            newsRepositoryImpl.upsertArticle(article)
        }
    }

    fun deleteArticle(article: ArticleDomain) {
        CoroutineScope(Dispatchers.IO).launch {
            newsRepositoryImpl.deleteArticle(article)
        }
    }



}