package com.nativecreativa.news_aggregator_app.Activities
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.nativecreativa.news_aggregator_app.Fragments.NewsFragment
import com.nativecreativa.news_aggregator_app.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      //  val toolbar = findViewById<Toolbar>(R.id.toolbar)
      //  setSupportActionBar(toolbar)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, NewsFragment())
            .commit()
    }
}
