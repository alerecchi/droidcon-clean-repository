package com.droidcon.cleanrepository.data.repository

import com.droidcon.cleanrepository.data.datasource.GitHubDataSource
import com.droidcon.cleanrepository.data.datasource.LocalDataSource
import com.droidcon.cleanrepository.data.datasource.TwitterDataSource
import com.droidcon.cleanrepository.data.kx.bindToLifecycle
import com.droidcon.cleanrepository.domain.model.Feed
import com.droidcon.cleanrepository.domain.model.NetworkState
import com.droidcon.cleanrepository.domain.repository.FeedRepository
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class DoubleSourceFeedRepository @Inject constructor(
    private val twitterRemoteDataSource: TwitterDataSource,
    private val gitHubRemoteDataSource: GitHubDataSource,
    private val roomLocalDataSource: LocalDataSource
) : FeedRepository() {

    override val networkState: PublishSubject<NetworkState> = PublishSubject.create()

    override fun getFeed(): Flowable<List<Feed>> {
        refreshFeed()
        networkState.onNext(NetworkState.LOADING)
        return roomLocalDataSource.getFeed()
            .doOnNext { networkState.onNext(NetworkState.COMPLETED) }
    }

    override fun refreshFeed() {
        Single.zip(
            twitterRemoteDataSource.getTimeline(),
            gitHubRemoteDataSource.getPage(),
            BiFunction<List<Feed>, List<Feed>, List<Feed>> { twitterFeeds, githubFeeds ->
                twitterFeeds.plus(githubFeeds)
            })
            .subscribe({
                roomLocalDataSource.insertFeeds(it)
            }, {
                it.printStackTrace()
                networkState.onNext(NetworkState.ERROR)
            }).bindToLifecycle(this)
    }
}