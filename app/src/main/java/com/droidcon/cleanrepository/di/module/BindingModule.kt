package com.droidcon.cleanrepository.di.module

import com.droidcon.cleanrepository.data.datasource.*
import com.droidcon.cleanrepository.data.repository.DoubleSourceFeedRepository
import com.droidcon.cleanrepository.data.repository.LocalPagedRepository
import com.droidcon.cleanrepository.data.repository.RemotePagedRepository
import com.droidcon.cleanrepository.data.repository.SingleSourceFeedRepository
import com.droidcon.cleanrepository.di.qualifier.Double
import com.droidcon.cleanrepository.di.qualifier.Local
import com.droidcon.cleanrepository.di.qualifier.Remote
import com.droidcon.cleanrepository.di.qualifier.Single
import com.droidcon.cleanrepository.domain.repository.FeedRepository
import com.droidcon.cleanrepository.domain.repository.PagedRepository
import dagger.Binds
import dagger.Module

@Module
abstract class BindingModule {

    @Binds
    @Single
    abstract fun providesSingleRepository(feedRepository: SingleSourceFeedRepository): FeedRepository

    @Binds
    @Double
    abstract fun providesDoubleRepository(feedRepository: DoubleSourceFeedRepository): FeedRepository

    @Binds
    @Local
    abstract fun providesLocalPagedRepository(pagedRepository: LocalPagedRepository): PagedRepository

    @Binds
    @Remote
    abstract fun providesRemotePagedRepository(pagedRepository: RemotePagedRepository): PagedRepository

    @Binds
    abstract fun providesTwitterDataSource(twitterDataSourceImpl: TwitterDataSourceImpl): TwitterDataSource

    @Binds
    abstract fun providesLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource

    @Binds
    abstract fun providesGithubDataSource(gitHubDataSourceImpl: GitHubDataSourceImpl): GitHubDataSource
}