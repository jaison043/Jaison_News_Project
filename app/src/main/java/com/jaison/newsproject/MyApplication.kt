package com.jaison.newsproject

import android.app.Application
import com.jaison.newsproject.data.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate(){
        super.onCreate()
        // start Koin!
        startKoin {
            // declare used Android context
            androidContext(this@MyApplication)
            // declare modules
           modules(viewModelModule)
        }
    }
}