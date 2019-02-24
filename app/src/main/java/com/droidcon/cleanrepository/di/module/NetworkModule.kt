package com.droidcon.cleanrepository.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    internal fun providesGson() = GsonBuilder().create()

    @Singleton
    @Provides
    internal fun providesOkHttp() = OkHttpClient.Builder().build()

    @Provides
    internal fun providesRetrofitBuilder(client: OkHttpClient, gson: Gson) = Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())

}