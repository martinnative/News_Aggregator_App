package com.nativecreativa.news_aggregator_app.ViewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nativecreativa.news_aggregator_app.Adapters.NewsAdapter
import com.nativecreativa.news_aggregator_app.Models.NewsArticle
import com.nativecreativa.news_aggregator_app.R

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
    private val authorTextView: TextView = itemView.findViewById(R.id.authorTextView)
    private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
    private val publishedAtTextView: TextView = itemView.findViewById(R.id.dateTextView)
    private val imageView: ImageView = itemView.findViewById(R.id.imageView)

    fun bind(article: NewsArticle, listener: NewsAdapter.OnItemClickListener) {
        titleTextView.text = article.title
        authorTextView.text = article.author
        descriptionTextView.text = article.description
        publishedAtTextView.text = article.publishedAt
        Glide.with(imageView.context)
            .load(article.urlToImage)
            .into(imageView)
        itemView.setOnClickListener { listener.onItemClick(article) }
    }
}