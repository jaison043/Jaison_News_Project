package com.jaison.newsproject.ui.main

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jaison.newsproject.LOG_TAG
import com.jaison.newsproject.R
import com.jaison.newsproject.data.ArticleItem
import com.jaison.newsproject.data.NewsFeed

class NewsRecyclerAdapter(
    val context: Context,
    val news: NewsFeed,
    val itemListener: NewsItemListener
) :
    RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.title)
        val description = itemView.findViewById<TextView>(R.id.description)
        val author = itemView.findViewById<TextView>(R.id.author)
        val newsImage = itemView.findViewById<ImageView>(R.id.newsimage)
    }

    override fun getItemCount() = news.articles.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.news_item_card, parent, false)


        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsitem = news.articles[position]

        with(holder) {
            title?.let {
                it.text = newsitem.title
            }
            description?.let {
                it.text = newsitem.description
            }
            author?.let {
                it.text = newsitem.author
            }
            newsitem.author.let {
                author.visibility=View.VISIBLE
            }

            newsImage?.let {
                Glide.with(context).load(newsitem.urlToImage).into(newsImage)
            }

            holder.itemView.setOnClickListener {
                itemListener.onNewsItemClick(newsitem)
            }
        }

    }



    interface NewsItemListener {
        fun onNewsItemClick(newsitem: ArticleItem)

    }
}