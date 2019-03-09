package com.droidcon.cleanrepository.data.repository

import androidx.paging.DataSource
import com.droidcon.cleanrepository.data.datasource.LocalDataSource
import com.droidcon.cleanrepository.data.datasource.TwitterRemoteDataSource
import com.droidcon.cleanrepository.domain.model.Feed
import com.droidcon.cleanrepository.domain.repository.PaginatedFeedRepository
import javax.inject.Inject

class PaginatedRepository @Inject constructor(
    private val twitterRemoteDataSource: TwitterRemoteDataSource,
    private val roomLocalDataSource: LocalDataSource
) : PaginatedFeedRepository() {

    override fun getPaginatedFeed(): DataSource.Factory<Int, Feed> {
        return roomLocalDataSource.getPaginatedFeeds()
    }
}