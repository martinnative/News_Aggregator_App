package com.nativecreativa.news_aggregator_app.Repositories

import com.nativecreativa.news_aggregator_app.Models.NewsArticle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException

class NewsRepository {
    private val apiKey = "41337a1eee9b4a56bbec84278fd35c5b"
    private val baseUrl = "https://newsapi.org/v2/top-headlines?country=us"
    private val baseUrl1 = "https://newsapi.org/v2/everything?q="

    suspend fun getNews(keyword: String = ""): List<NewsArticle> {
        val client = OkHttpClient()
        val requestBuilder = Request.Builder()
            .url("$baseUrl&apiKey=$apiKey")

        if (keyword.isNotEmpty()) {
            requestBuilder.url("$baseUrl1$keyword&apiKey=$apiKey")
        }

        val request = requestBuilder.build()

        val response = withContext(Dispatchers.IO) { client.newCall(request).execute() }

        if (!response.isSuccessful) {
            throw IOException("Unexpected code $response")
        }

        val json = JSONObject(response.body.string())
        val articlesJson = json.getJSONArray("articles")

        val articles = mutableListOf<NewsArticle>()

        for (i in 0 until articlesJson.length()) {
            val articleJson = articlesJson.getJSONObject(i)
            val title = articleJson.getString("title")
            val author = articleJson.optString("author", null)
            val description = articleJson.optString("description", null)
            val urlToImage = articleJson.optString("urlToImage", null)
            val url = articleJson.getString("url")
            val publishedAt = articleJson.getString("publishedAt")
            var content = articleJson.getString("content")
           // for(i in 1..10){content +=articleJson.getString("content")}
            val article = NewsArticle(title, author, description, urlToImage, url, publishedAt,content)
            articles.add(article)
        }

        return articles
    }
}
