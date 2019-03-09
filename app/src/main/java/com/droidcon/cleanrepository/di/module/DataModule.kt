package com.droidcon.cleanrepository.di.module

import android.content.Context
import androidx.room.Room
import com.droidcon.cleanrepository.data.datasource.GitHubDataSourceImpl
import com.droidcon.cleanrepository.data.datasource.LocalDataSourceImpl
import com.droidcon.cleanrepository.data.datasource.TwitterDataSourceImpl
import com.droidcon.cleanrepository.data.persistence.AppDatabase
import com.droidcon.cleanrepository.data.repository.DoubleRepository
import com.droidcon.cleanrepository.data.repository.PagedRepositoryImpl
import com.droidcon.cleanrepository.data.repository.SingleRepositoryImpl
import com.droidcon.cleanrepository.data.service.GithubService
import com.droidcon.cleanrepository.data.service.TwitterService
import com.droidcon.cleanrepository.domain.repository.FeedRepository
import com.droidcon.cleanrepository.domain.repository.PagedRepository
import com.hm.goe.base.di.qualifier.Double
import com.hm.goe.base.di.qualifier.Single
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun providesGithubService(retrofit: Retrofit) = retrofit.create(GithubService::class.java)

    @Singleton
    @Provides
    fun providesTwitterService(retrofit: Retrofit) = retrofit.create(TwitterService::class.java)

    @Singleton
    @Provides
    fun providesDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "jake-database").build()
    }

    @Singleton
    @Provides
    fun providesDatabaseDao(database: AppDatabase) = database.feedDao()

    @Provides
    @Single
    fun providesSingleRepository(
        twitterDataSource: TwitterDataSourceImpl,
        roomDataSource: LocalDataSourceImpl
    ): FeedRepository = SingleRepositoryImpl(twitterDataSource, roomDataSource)

    @Provides
    @Double
    fun providesDoubleRepository(
        twitterDataSource: TwitterDataSourceImpl,
        gitHubDataSource: GitHubDataSourceImpl,
        roomDataSource: LocalDataSourceImpl
    ): FeedRepository = DoubleRepository(twitterDataSource, gitHubDataSource, roomDataSource)

    @Provides
    fun providesPagedRepository(
        twitterDataSource: TwitterDataSourceImpl,
        roomDataSource: LocalDataSourceImpl
    ): PagedRepository = PagedRepositoryImpl(twitterDataSource, roomDataSource)

}