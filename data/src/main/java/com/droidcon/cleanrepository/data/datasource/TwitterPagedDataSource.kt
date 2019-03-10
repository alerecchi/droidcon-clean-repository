package com.droidcon.cleanrepository.data.datasource

import androidx.paging.ItemKeyedDataSource
import com.droidcon.cleanrepository.data.kx.bindToLifecycle
import com.droidcon.cleanrepository.data.service.TwitterService
import com.droidcon.cleanrepository.domain.SubscriptionContainer
import com.droidcon.cleanrepository.domain.model.Feed
import com.droidcon.cleanrepository.domain.model.NetworkState
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class TwitterPagedDataSource @Inject constructor(twitterService: TwitterService) : ItemKeyedDataSource<String, Feed>(),
    TwitterDataSource by TwitterDataSourceImpl(twitterService) {

    lateinit var subscriptionContainer: SubscriptionContainer
    var networkState: PublishSubject<NetworkState>? = null

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<Feed>) {
        networkState?.onNext(NetworkState.LOADING)
        remoteCall(
            getTimeline(
                firstTweetId = params.requestedInitialKey?.toLong(),
                pageSize = params.requestedLoadSize
            ), callback
        )
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<Feed>) {
        remoteCall(
            getTimeline(
                lastTweetId = params.key.split("_")[1].toLong(),
                pageSize = params.requestedLoadSize
            ), callback
        )
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<Feed>) {
        remoteCall(
            getTimeline(
                firstTweetId = params.key.split("_")[1].toLong(),
                pageSize = params.requestedLoadSize
            ), callback
        )
    }

    override fun getKey(item: Feed) = item.id

    private fun remoteCall(single: Single<List<Feed>>, callback: LoadCallback<Feed>) {
        networkState?.onNext(NetworkState.LOADING)
        single.subscribe({
            callback.onResult(it)
            networkState?.onNext(NetworkState.COMPLETED)
        }, {
            networkState?.onNext(NetworkState.ERROR)
        }).bindToLifecycle(subscriptionContainer)
    }
}