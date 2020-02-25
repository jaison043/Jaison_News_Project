package com.jaison.newsproject.data

data class NewsFeed(

    val articles: List<ArticleItem>

)

data class ArticleItem(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)