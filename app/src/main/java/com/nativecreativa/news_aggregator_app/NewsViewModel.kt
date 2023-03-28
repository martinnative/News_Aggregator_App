package com.nativecreativa.news_aggregator_app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val newsRepository = NewsRepository()
    private val _news = MutableLiveData<List<NewsArticle>>()

    val news: LiveData<List<NewsArticle>> = _news

    init {
        viewModelScope.launch {
            _news.value = newsRepository.getNews()
        }
    }
}
