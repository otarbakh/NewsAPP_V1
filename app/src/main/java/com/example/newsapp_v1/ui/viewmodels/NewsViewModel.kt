package com.example.newsapp_v1.ui.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp_v1.common.util.Resource
import com.example.newsapp_v1.data.local.models.ArticlesEntity
import com.example.newsapp_v1.data.remote.models.Article
import com.example.newsapp_v1.domain.ArticlesRepository
import com.example.newsapp_v1.domain.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepositoryImpl: NewsRepository,
    private val articlesRepository: ArticlesRepository
) : ViewModel() {

    private val _newsState = MutableStateFlow<Resource<List<Article>>>(Resource.Loading(false))
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

    fun getArticle():Flow<List<ArticlesEntity>>{
        return articlesRepository.getArticle()
    }

    fun upsertArticle(article: ArticlesEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            articlesRepository.upsertArticle(article)
        }
    }

    fun deleteArticle(article: ArticlesEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            articlesRepository.deleteArticle(article)
        }
    }

}