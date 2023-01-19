package com.example.newsapp_v1.ui.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp_v1.common.util.Resource
import com.example.newsapp_v1.data.local.models.ArticlesEntity
import com.example.newsapp_v1.domain.models.ArticleDomain
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
) : ViewModel() {

    private val _newsState = MutableStateFlow<Resource<List<ArticleDomain>>>(Resource.Loading(false))
    val newsState = _newsState.asStateFlow()

     fun getBreakingNewsFromViewModel(){
        viewModelScope.launch {
            newsRepositoryImpl.getBreakingNews().collect() {
                when (it){
                    is Resource.Success -> _newsState.value = Resource.Success(it.data)
                    is Resource.Loading -> _newsState.value = Resource.Loading(true)
                    is Resource.Error -> _newsState.value = Resource.Error("ops error")
                }
            }
        }
    }

    suspend fun getSavedArticle():Flow<List<ArticleDomain>>{
        return newsRepositoryImpl.getArticle()
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