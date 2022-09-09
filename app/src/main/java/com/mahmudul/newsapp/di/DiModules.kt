package com.mahmudul.newsapp.di

import com.mahmudul.newsapp.repository.NewsRepository
import com.mahmudul.newsapp.service.NewsInterface
import com.mahmudul.newsapp.utils.Constants
import java.util.concurrent.TimeUnit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiModules {
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(15, TimeUnit.SECONDS) // connect timeout
            .readTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsInterface(retrofit: Retrofit): NewsInterface {
        return retrofit.create(NewsInterface::class.java)
    }


    @Singleton
    @Provides
    fun provideRepository(newsInterface: NewsInterface): NewsRepository{
        return NewsRepository(newsInterface)
    }

}