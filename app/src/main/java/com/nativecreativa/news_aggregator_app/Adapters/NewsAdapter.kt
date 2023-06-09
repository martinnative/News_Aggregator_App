package com.nativecreativa.news_aggregator_app.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nativecreativa.news_aggregator_app.Models.NewsArticle
import com.nativecreativa.news_aggregator_app.ViewHolders.NewsViewHolder
import com.nativecreativa.news_aggregator_app.R

class NewsAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<NewsViewHolder>() {

    private val items = mutableListOf<NewsArticle>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(newsList: List<NewsArticle>) {
        items.clear()
        items.addAll(newsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = items[position]
        holder.itemView.apply {
            holder.bind(items[position], listener)
            setOnClickListener { listener.onItemClick(article) }
        }
    }

    override fun getItemCount() = items.size
    interface OnItemClickListener {
        fun onItemClick(article: NewsArticle)
    }
}
