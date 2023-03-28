package com.nativecreativa.news_aggregator_app

import NewsAdapter
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NewsFragment : Fragment() {
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)
        recyclerView = view.findViewById(R.id.newsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val adapter = NewsAdapter(object : NewsAdapter.OnItemClickListener {
            override fun onItemClick(article: NewsArticle) {
               // val intent = Intent(context, ArticleActivity::class.java)
               // intent.putExtra("article", article)
               // context?.startActivity(intent)
            }
        })

        recyclerView.adapter = adapter

        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        newsViewModel.news.observe(viewLifecycleOwner) { articles ->
            adapter.setItems(articles)
        }

        return view
    }
}

