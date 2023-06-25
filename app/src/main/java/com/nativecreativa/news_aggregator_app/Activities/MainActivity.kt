package com.nativecreativa.news_aggregator_app.Activities
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.nativecreativa.news_aggregator_app.Adapters.NewsAdapter
import com.nativecreativa.news_aggregator_app.Fragments.NewsFragment
import com.nativecreativa.news_aggregator_app.R
import com.nativecreativa.news_aggregator_app.ViewModels.NewsViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var searchEditText: EditText
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var searchButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, NewsFragment())
            .commit()

        //val toolbar = findViewById<Toolbar>(R.id.toolbar)
        //setSupportActionBar(toolbar)






    }
}
