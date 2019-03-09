package com.droidcon.cleanrepository.data.repository

import com.droidcon.cleanrepository.data.datasource.GitHubRemoteDataSource
import com.droidcon.cleanrepository.data.datasource.LocalDataSource
import com.droidcon.cleanrepository.data.datasource.TwitterRemoteDataSource
import com.droidcon.cleanrepository.data.kx.bindToLifecycle
import com.droidcon.cleanrepository.domain.model.Feed
import com.droidcon.cleanrepository.domain.repository.FeedRepository
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class DoubleSourceFeedRepository @Inject constructor(
    private val twitterRemoteDataSource: TwitterRemoteDataSource,
    private val gitHubRemoteDataSource: GitHubRemoteDataSource,
    private val roomLocalDataSource: LocalDataSource
) : FeedRepository() {

    override fun getFeed(): Flowable<List<Feed>> {
        refreshFeed()
        return roomLocalDataSource.getFeed()
    }

    override fun refreshFeed() {
        Single.zip(
            twitterRemoteDataSource.getTimeline(),
            gitHubRemoteDataSource.getPage(),
            BiFunction<List<Feed>, List<Feed>, List<Feed>> { twitterFeeds, githubFeeds ->
                twitterFeeds
                    .plus(githubFeeds)
                    .sortedBy { it.date }
            })
            .subscribe({
                roomLocalDataSource.insertFeeds(it)
            }, {
                it.printStackTrace()
            }).bindToLifecycle(this)
    }
}