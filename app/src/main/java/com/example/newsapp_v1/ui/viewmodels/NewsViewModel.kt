package com.example.newsapp_v1.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp_v1.common.util.Resource
import com.example.newsapp_v1.data.model.Article
import com.example.newsapp_v1.data.repository.NewsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepositoryImpl: NewsRepositoryImpl
) : ViewModel() {
    private val _newsState = MutableStateFlow<Resource<List<Article>>>(Resource.Loading(false))
    val newsState = _newsState.asStateFlow()

    suspend fun getBreakingNewsFromViewModel(): Flow<Resource<Article>> = flow {
        val breakingNews = newsRepositoryImpl.getBreakingNews()

        viewModelScope.launch {
            try {
                emit(Resource.Loading(true))
                emit(Resource.Success(breakingNews.))

            } catch (e: HttpException) {

            }
        }
    }

}