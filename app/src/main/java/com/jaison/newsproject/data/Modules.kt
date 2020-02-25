package com.jaison.newsproject.data

import com.jaison.newsproject.ui.shared.SharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule :Module= module {
    viewModel { SharedViewModel(get()) }
}