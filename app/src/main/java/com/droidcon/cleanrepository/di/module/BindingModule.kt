package com.droidcon.cleanrepository.di.module

import com.droidcon.cleanrepository.data.datasource.*
import com.droidcon.cleanrepository.data.repository.DoubleRepository
import com.droidcon.cleanrepository.data.repository.PagedRepositoryImpl
import com.droidcon.cleanrepository.data.repository.SingleRepositoryImpl
import com.droidcon.cleanrepository.domain.repository.FeedRepository
import com.droidcon.cleanrepository.domain.repository.PagedRepository
import com.hm.goe.base.di.qualifier.Double
import com.hm.goe.base.di.qualifier.Single
import dagger.Binds
import dagger.Module

@Module
abstract class BindingModule {

    @Binds
    @Single
    abstract fun providesSingleRepository(singleRepository: SingleRepositoryImpl): FeedRepository

    @Binds
    @Double
    abstract fun providesDoubleRepository(doubleRepository: DoubleRepository): FeedRepository

    @Binds
    abstract fun providesPagedRepository(pagedRepository: PagedRepositoryImpl): PagedRepository

    @Binds
    abstract fun providesTwitterDataSource(twitterDataSourceImpl: TwitterDataSourceImpl): TwitterDataSource

    @Binds
    abstract fun providesLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource

    @Binds
    abstract fun providesGithubDataSource(gitHubDataSourceImpl: GitHubDataSourceImpl): GitHubDataSource
}