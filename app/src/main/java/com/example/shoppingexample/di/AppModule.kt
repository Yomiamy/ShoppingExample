package com.example.shoppingexample.di

import com.example.shoppingexample.flow.main.viewmodel.MainViewModel
import com.example.shoppingexample.net.api.ApiManager
import com.example.shoppingexample.repository.ApiRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

// 放ViewModel
val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

// 放Domain layer
val domainModule = module {
}

// 放Repository
val repositoryModule = module {
    single { ApiRepository(get()) }
}

// 放其它data等
val utilsModule = module {
    single { ApiManager.buildAPI() }
}