package com.nativecreativa.news_aggregator_app.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nativecreativa.news_aggregator_app.Adapters.NewsAdapter
import com.nativecreativa.news_aggregator_app.Models.NewsArticle
import com.nativecreativa.news_aggregator_app.Repositories.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val newsRepository = NewsRepository()
    private val _news = MutableLiveData<List<NewsArticle>>()
    private val _keyword = MutableLiveData<String>()
    private lateinit var newsAdapter: NewsAdapter

    val news: LiveData<List<NewsArticle>> = _news
    val keyword: LiveData<String> = _keyword

    fun setKeyword(searchTerm:String){
        _keyword.value = searchTerm
    }
    fun getKeyword(): MutableLiveData<String> {return _keyword}
    fun searchNews(keyword: String) {
        viewModelScope.launch {
            val articles = newsRepository.getNews(keyword)
            _news.value = articles
        }
    }
    fun reInit() {
        viewModelScope.launch {
            _news.value = newsRepository.getNews()
        }
    }


}
