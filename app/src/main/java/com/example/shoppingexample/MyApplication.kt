package com.example.shoppingexample

import android.annotation.SuppressLint
import android.app.Application
import com.example.shoppingexample.di.domainModule
import com.example.shoppingexample.di.repositoryModule
import com.example.shoppingexample.di.utilsModule
import com.example.shoppingexample.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {

    @SuppressLint("CheckResult")
    override fun onCreate() {
        super.onCreate()

        // Koin依賴注入Module初始
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MyApplication)

            modules(
                viewModelModule,
                domainModule,
                repositoryModule,
                utilsModule
            )
        }
    }
}