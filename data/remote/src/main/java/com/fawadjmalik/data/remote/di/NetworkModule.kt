package com.fawadjmalik.data.remote.di

import com.fawadjmalik.data.remote.BuildConfig
import com.fawadjmalik.data.remote.datasource.RemoteDataSource
import com.fawadjmalik.data.remote.datasource.RemoteSource
import com.fawadjmalik.data.remote.network.MarsPhotosApi

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Author: Muhammad Fawad Jawaid Malik
 * This is the Hilt NetworkModule class which provides the retrofit, client, service and repositories dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideMarsRoverService(retrofit: Retrofit): MarsPhotosApi {
        return retrofit.create(MarsPhotosApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteRepository(api: MarsPhotosApi): RemoteSource {
        return RemoteDataSource(api)
    }
}
