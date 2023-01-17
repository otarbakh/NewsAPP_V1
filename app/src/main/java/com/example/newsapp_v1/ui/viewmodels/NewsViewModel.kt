package com.example.newsapp_v1.ui.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp_v1.common.util.Resource
import com.example.newsapp_v1.data.model.Article
import com.example.newsapp_v1.data.repository.NewsRepositoryImpl
import com.example.newsapp_v1.domain.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepositoryImpl: NewsRepository
) : ViewModel() {

    private val _newsState = MutableStateFlow<Resource<List<Article>>>(Resource.Loading(false))
    val newsState = _newsState.asStateFlow()

//    private val _test = MutableSharedFlow()<String>()
//    val test = _test.asStateFlow()

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

//    fun gettest(){
//        viewModelScope.launch {
//            _test.value = "misho"
//        }
//
//    }

}