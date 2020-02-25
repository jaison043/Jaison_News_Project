package com.jaison.newsproject.data

import retrofit2.Response
import retrofit2.http.GET

interface NewsService {

   @GET("/v2/top-headlines?country=us&category=entertainment&apiKey=6175ad2d5f274d919c4ef2c4991eec94")
   suspend fun getNewsData(): Response<NewsFeed>
}