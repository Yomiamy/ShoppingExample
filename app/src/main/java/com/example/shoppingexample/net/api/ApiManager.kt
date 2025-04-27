package com.example.shoppingexample.net.api

import android.content.Context
import com.example.shoppingexample.net.Constants
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit


object ApiManager {

    private const val TIMEOUT_IN_SECONDS = 60L

    private var sApiInstance: IApi? = null
    private val sLock = Any()

    fun buildAPI(): IApi = synchronized(sLock) {
        if(sApiInstance != null) {
            return sApiInstance!!
        }

        sApiInstance = createApiInstance(IApi::class.java)

        return sApiInstance!!
    }


    private fun <T> createApiInstance(apiClz: Class<T>): T {
        val builder = Builder()
            .connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)

        // 設置OkHttpClient
        val client: OkHttpClient = builder.build()

        // 設置Response GSON convert
        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .serializeNulls()
            .create()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Constants.API_DOMAIN)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()

        return retrofit.create(apiClz)
    }
}