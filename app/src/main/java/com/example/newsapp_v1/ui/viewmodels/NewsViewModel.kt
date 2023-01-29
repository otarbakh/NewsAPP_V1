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

    private val _searchedQuerry = MutableStateFlow<String>("chatgtp")
    val searchedQuerry = _searchedQuerry.asStateFlow()

    val collectedSearchQuerry = searchedQuerry.flatMapLatest { querry ->
        newsPagingRepository.getBreakingNews(querry).cachedIn(viewModelScope)
    }



    fun search(q:String){
        _searchedQuerry.value = q
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

    suspend fun getSavedArticle():Flow<List<ArticleDomain>>{
        return newsRepositoryImpl.getArticle()
    }
}