package com.madd.madd.twitterkapp.http

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class APIModule {

    private val BASE_URL = "https://wizetwitterproxy.herokuapp.com"
    private val CACHE_SIZE = 10 * 1024 * 1024 // 10MB


    @Provides
    fun provideHttpClient(context: Context): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS

        return OkHttpClient.Builder()
            .cache(Cache(context.cacheDir, CACHE_SIZE.toLong()))
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(baseURL:String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApi(context: Context): API {
        return provideRetrofit(BASE_URL,provideHttpClient(context)).create(API::class.java)
    }

}