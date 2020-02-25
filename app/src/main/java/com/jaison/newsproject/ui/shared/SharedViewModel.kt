package com.jaison.newsproject.ui.shared

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jaison.newsproject.data.ArticleItem
import com.jaison.newsproject.data.NewsFeed
import com.jaison.newsproject.data.NewsFeedRepository

class SharedViewModel(app:Application) : AndroidViewModel(app) {

    private val dataRepo=NewsFeedRepository(app)
    val newsData=dataRepo.newsData
    val selectedNewsItem=MutableLiveData<ArticleItem>()
}
