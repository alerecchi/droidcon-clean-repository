package com.droidcon.cleanrepository.di.module

import android.content.Context
import androidx.room.Room
import com.droidcon.cleanrepository.data.datasource.GitHubDataSource
import com.droidcon.cleanrepository.data.datasource.RoomDataSource
import com.droidcon.cleanrepository.data.datasource.TwitterDataSource
import com.droidcon.cleanrepository.data.persistence.AppDatabase
import com.droidcon.cleanrepository.data.repository.DoubleRepository
import com.droidcon.cleanrepository.data.repository.PaginatedRepository
import com.droidcon.cleanrepository.data.repository.SingleRepository
import com.droidcon.cleanrepository.data.service.GithubService
import com.droidcon.cleanrepository.data.service.TwitterService
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
    internal fun providesDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "jake-database").build()
    }

    @Singleton
    @Provides
    internal fun providesDatabaseDao(database: AppDatabase) = database.feedDao()

    @Provides
    internal fun providesSingleRepository(
        twitterDataSource: TwitterDataSource,
        roomDataSource: RoomDataSource
    ): SingleRepository = SingleRepository(twitterDataSource, roomDataSource)

    @Provides
    internal fun providesDoubleRepository(
        twitterDataSource: TwitterDataSource,
        gitHubDataSource: GitHubDataSource,
        roomDataSource: RoomDataSource
    ): DoubleRepository = DoubleRepository(twitterDataSource, gitHubDataSource, roomDataSource)


    @Provides
    internal fun providesPaginatedRepository(
        twitterDataSource: TwitterDataSource,
        roomDataSource: RoomDataSource
    ): PaginatedRepository = PaginatedRepository(twitterDataSource, roomDataSource)

}