package com.nativecreativa.news_aggregator_app.Activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.nativecreativa.news_aggregator_app.Models.NewsArticle
import com.nativecreativa.news_aggregator_app.R

class ArticleActivity : AppCompatActivity() {
    private lateinit var titleTextView: TextView
    private lateinit var authorTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        titleTextView = findViewById(R.id.articleTitleTextView)
        authorTextView = findViewById(R.id.articleAuthorTextView)
        descriptionTextView = findViewById(R.id.articleContentTextView)
        imageView = findViewById(R.id.articleImageView)

        val article = intent.getParcelableExtra("article") as NewsArticle?

        if (article != null) {
            titleTextView.text = article.title
            authorTextView.text = article.author
            descriptionTextView.text = article.content
        }
        Glide.with(imageView.context)
            .load(article?.urlToImage)
            .into(imageView)

       //  button.setOnClickListener {
       //    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(article?.url))
       //    startActivity(browserIntent)
       // }
    }
}
