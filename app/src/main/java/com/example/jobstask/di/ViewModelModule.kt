package com.example.jobstask.di

import com.example.jobstask.viewmodel.JobsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    // Specific viewModel pattern to tell Koin how to build CountriesViewModel
    viewModel {
        JobsViewModel(repository = get())
    }

}