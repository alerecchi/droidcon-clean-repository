package com.droidcon.cleanrepository.di.module

import android.content.Context
import androidx.room.Room
import com.droidcon.cleanrepository.data.persistence.AppDatabase
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
}