package com.droidcon.cleanrepository.data.repository

import com.droidcon.cleanrepository.data.datasource.LocalDataSource
import com.droidcon.cleanrepository.data.datasource.TwitterRemoteDataSource
import com.droidcon.cleanrepository.data.kx.bindToLifecycle
import com.droidcon.cleanrepository.domain.model.Feed
import com.droidcon.cleanrepository.domain.repository.FeedRepository
import io.reactivex.Flowable
import javax.inject.Inject

class SingleSourceFeedRepository @Inject constructor(
    private val twitterRemoteDataSource: TwitterRemoteDataSource,
    private val roomLocalDataSource: LocalDataSource
) : FeedRepository() {

    override fun getFeed(): Flowable<List<Feed>> {
        refreshFeed()
        return roomLocalDataSource.getFeed()
    }

    override fun refreshFeed() {
        twitterRemoteDataSource.getTimeline()
            .map { list -> list.sortedBy { it.date } }
            .subscribe({
                roomLocalDataSource.insertFeeds(it)
            }, {
                it.printStackTrace()
            }).bindToLifecycle(this)
    }
}