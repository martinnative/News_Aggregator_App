import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nativecreativa.news_aggregator_app.NewsArticle
import com.nativecreativa.news_aggregator_app.R

class NewsAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val items = mutableListOf<NewsArticle>()

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
        holder.bind(items[position], listener)
    }

    override fun getItemCount() = items.size

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val authorTextView: TextView = itemView.findViewById(R.id.authorTextView)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        private val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)

        fun bind(article: NewsArticle, listener: OnItemClickListener) {
            Glide.with(imageView.context)
                .load(article.urlToImage)
                .placeholder(R.drawable.placeholder_image)
                .into(imageView)
            titleTextView.text = article.title
            authorTextView.text = article.author
            descriptionTextView.text = article.description
            dateTextView.text = article.publishedAt
            itemView.setOnClickListener { listener.onItemClick(article) }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(article: NewsArticle)
    }
}
