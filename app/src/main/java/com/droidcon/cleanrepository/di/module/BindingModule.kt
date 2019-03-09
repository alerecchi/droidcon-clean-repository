package com.droidcon.cleanrepository.di.module

import com.droidcon.cleanrepository.data.repository.DoubleRepository
import com.droidcon.cleanrepository.data.repository.SingleRepositoryImpl
import com.droidcon.cleanrepository.domain.repository.FeedRepository
import com.hm.goe.base.di.qualifier.Double
import com.hm.goe.base.di.qualifier.Single
import dagger.Binds
import dagger.Module

@Module
abstract class BindingModule {

    @Binds
    @Single
    abstract fun providesSingleDataSource(singleRepository: SingleRepositoryImpl): FeedRepository

    @Binds
    @Double
    abstract fun providesDoubleDataSource(singleRepository: DoubleRepository): FeedRepository
}