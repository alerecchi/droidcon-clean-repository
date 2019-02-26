package com.droidcon.cleanrepository.di.module

import com.droidcon.cleanrepository.data.datasource.GitHubRemoteDataSource
import com.droidcon.cleanrepository.data.datasource.TwitterRemoteDataSource
import com.droidcon.cleanrepository.data.repository.RepositoryImpl
import com.droidcon.cleanrepository.data.service.GithubService
import com.droidcon.cleanrepository.data.service.TwitterService
import com.droidcon.cleanrepository.domain.repository.Repository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    internal fun providesGithubService(retrofit: Retrofit) = retrofit.create(GithubService::class.java)

    @Singleton
    @Provides
    internal fun providesTwitterService(retrofit: Retrofit) = retrofit.create(TwitterService::class.java)

    @Singleton
    @Provides
    internal fun providesRepository(
        twitterRemoteDataSource: TwitterRemoteDataSource,
        gitHubRemoteDataSource: GitHubRemoteDataSource
    ): Repository = RepositoryImpl(twitterRemoteDataSource, gitHubRemoteDataSource)

}