package com.nativecreativa.news_aggregator_app.Fragments

import com.nativecreativa.news_aggregator_app.Adapters.NewsAdapter
import android.content.Intent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nativecreativa.news_aggregator_app.Activities.ArticleActivity
import com.nativecreativa.news_aggregator_app.Models.NewsArticle
import com.nativecreativa.news_aggregator_app.ViewModels.NewsViewModel
import com.nativecreativa.news_aggregator_app.R

class NewsFragment : Fragment() {
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var keyword: String
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var searchEditText: EditText
    private lateinit var searchButton: Button


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)
        recyclerView = view.findViewById(R.id.newsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        newsAdapter = NewsAdapter(object : NewsAdapter.OnItemClickListener {
            override fun onItemClick(article: NewsArticle) {

                val intent = Intent(context, ArticleActivity::class.java)
                intent.putExtra("article", article)
                context?.startActivity(intent)
            }
        })

        recyclerView.adapter = newsAdapter

        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        newsViewModel.news.observe(viewLifecycleOwner) { articles ->
            newsAdapter.setItems(articles)
        }
        newsViewModel.reInit()


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchEditText = view.findViewById(R.id.searchBar)
        searchButton = view.findViewById(R.id.searchButton)
        searchButton.setOnClickListener{
            newsViewModel.setKeyword(searchEditText.text.toString())
        }
        newsViewModel.getKeyword().observe(viewLifecycleOwner){keyword ->
          newsViewModel.searchNews(keyword)
         }
        newsViewModel.news.observe(viewLifecycleOwner){
            newsAdapter.setItems(it)
        }
    }
}

