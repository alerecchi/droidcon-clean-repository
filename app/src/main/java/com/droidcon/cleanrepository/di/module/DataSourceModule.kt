package com.droidcon.cleanrepository.di.module

import com.droidcon.cleanrepository.data.service.GithubService
import com.droidcon.cleanrepository.data.service.TwitterService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class DataSourceModule {

    @Singleton
    @Provides
    internal fun providesGithubService(builder: Retrofit.Builder) = builder
        .baseUrl("https://api.twitter.com/1.1/")
        .build()
        .create(GithubService::class.java)

    @Singleton
    @Provides
    internal fun providesTwitterService(builder: Retrofit.Builder) = builder
        .baseUrl("https://api.github.com/")
        .build()
        .create(TwitterService::class.java)

}