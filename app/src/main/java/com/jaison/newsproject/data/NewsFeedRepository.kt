package com.jaison.newsproject.data

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.jaison.newsproject.LOG_TAG
import com.jaison.newsproject.WEB_SERVICE_URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class NewsFeedRepository(val app: Application) {

    val newsData = MutableLiveData<NewsFeed>()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            callNewsService()
        }

        Log.i(LOG_TAG, "Network Available ${networkAvailable()}")
    }

    @WorkerThread
    suspend fun callNewsService() {
        if (networkAvailable()) {

            //Adding logging interceptor
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)

            //Retrofit Builder
            val retrofit = Retrofit.Builder()
                .baseUrl(WEB_SERVICE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(httpClient.build())
                .build()
            val service = retrofit.create(NewsService::class.java)
            val serviceData = service.getNewsData().body()
            newsData.postValue(serviceData)

        }

    }

    @Suppress("DEPRECATION")
    private fun networkAvailable(): Boolean {
        val connectivityManager =
            app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false

    }

}