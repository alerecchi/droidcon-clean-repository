package com.droidcon.cleanrepository.di.module

import com.droidcon.cleanrepository.data.datasource.*
import com.droidcon.cleanrepository.data.repository.DoubleSourceFeedRepository
import com.droidcon.cleanrepository.data.repository.PagedRepositoryImpl
import com.droidcon.cleanrepository.data.repository.SingleSourceFeedRepository
import com.droidcon.cleanrepository.di.qualifier.Double
import com.droidcon.cleanrepository.di.qualifier.Single
import com.droidcon.cleanrepository.domain.repository.FeedRepository
import com.droidcon.cleanrepository.domain.repository.PagedRepository
import dagger.Binds
import dagger.Module

@Module
abstract class BindingModule {

    @Binds
    @Single
    abstract fun providesSingleRepository(singleRepository: SingleSourceFeedRepository): FeedRepository

    @Binds
    @Double
    abstract fun providesDoubleRepository(doubleRepository: DoubleSourceFeedRepository): FeedRepository

    @Binds
    abstract fun providesPagedRepository(pagedRepository: PagedRepositoryImpl): PagedRepository

    @Binds
    abstract fun providesTwitterDataSource(twitterDataSourceImpl: TwitterDataSourceImpl): TwitterDataSource

    @Binds
    abstract fun providesLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource

    @Binds
    abstract fun providesGithubDataSource(gitHubDataSourceImpl: GitHubDataSourceImpl): GitHubDataSource
}