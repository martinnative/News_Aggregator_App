package com.nativecreativa.news_aggregator_app.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nativecreativa.news_aggregator_app.Adapters.NewsAdapter
import com.nativecreativa.news_aggregator_app.Models.NewsArticle
import com.nativecreativa.news_aggregator_app.R
import com.nativecreativa.news_aggregator_app.ViewModels.NewsViewModel

class ArticleActivity : AppCompatActivity() {
    private lateinit var titleTextView: TextView
    private lateinit var authorTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var imageView: ImageView
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var articleRecyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        titleTextView = findViewById(R.id.articleTitleTextView)
        authorTextView = findViewById(R.id.articleAuthorTextView)
        descriptionTextView = findViewById(R.id.articleContentTextView)
        imageView = findViewById(R.id.articleImageView)
        articleRecyclerView = findViewById(R.id.articleRecyclerView)
        articleRecyclerView.layoutManager = LinearLayoutManager(this)
        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        newsAdapter = NewsAdapter(object : NewsAdapter.OnItemClickListener {
            override fun onItemClick(article: NewsArticle) {

                val intent = Intent(this@ArticleActivity, ArticleActivity::class.java)
                intent.putExtra("article", article)
                this@ArticleActivity.startActivity(intent)
            }
        })
        articleRecyclerView.adapter = newsAdapter

        val article = intent.getParcelableExtra("article") as NewsArticle?

        if (article != null) {
            titleTextView.text = article.title
            authorTextView.text = article.author
            descriptionTextView.text = article.content
        }
        Glide.with(imageView.context)
            .load(article?.urlToImage)
            .into(imageView)

        newsViewModel.news.observe(this) {
            val articleNews = it.subList(1,it.size)
            newsAdapter.setItems(articleNews)
        }
        newsViewModel.reInit()
    }

}
