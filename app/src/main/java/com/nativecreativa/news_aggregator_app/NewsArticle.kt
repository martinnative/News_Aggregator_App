package com.nativecreativa.news_aggregator_app

data class NewsArticle(
    val title: String,
    val author: String?,
    val description: String?,
    val urlToImage: String?,
    val url: String,
    val publishedAt: String
)
