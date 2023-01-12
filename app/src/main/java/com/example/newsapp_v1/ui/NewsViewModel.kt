package com.example.newsapp_v1.ui

import androidx.lifecycle.ViewModel
import com.example.newsapp_v1.data.repository.NewsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class NewsViewModel(
    val newsRepositoryImpl: NewsRepositoryImpl
):ViewModel() {
}