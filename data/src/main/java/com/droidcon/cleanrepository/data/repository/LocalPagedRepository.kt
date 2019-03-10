package com.droidcon.cleanrepository.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.droidcon.cleanrepository.data.datasource.LocalDataSource
import com.droidcon.cleanrepository.data.datasource.TwitterDataSource
import com.droidcon.cleanrepository.data.kx.bindToLifecycle
import com.droidcon.cleanrepository.domain.model.Feed
import com.droidcon.cleanrepository.domain.model.NetworkState
import com.droidcon.cleanrepository.domain.repository.PagedRepository
import javax.inject.Inject

class LocalPagedRepository @Inject constructor(
    private val twitterRemoteDataSource: TwitterDataSource,
    private val roomLocalDataSource: LocalDataSource
) : PagedRepository() {

    private val config = PagedList.Config.Builder()
        .setPageSize(20)
        .setEnablePlaceholders(false)
        .build()
    private var lastFeedId: String? = null

    override fun <O> getPagedFeed(mapper: (Feed) -> (O)): LiveData<PagedList<O>> {
        return roomLocalDataSource.getPagedFeeds()
            .map { i -> mapper.invoke(i) }
            .toLiveData(
                config,
                boundaryCallback = object : PagedList.BoundaryCallback<O>() {
                    override fun onZeroItemsLoaded() {
                        networkState.onNext(NetworkState.LOADING)
                        fetchNextFeed()
                    }

                    override fun onItemAtEndLoaded(lastItem: O) {
                        lastFeedId?.let {
                            fetchNextFeed(it.split("_")[1].toLong())
                        }
                    }
                }
            )
    }

    private fun fetchNextFeed(lastId: Long? = null) {
        networkState.onNext(NetworkState.LOADING)
        twitterRemoteDataSource.getTimeline(lastTweetId = lastId)
            .subscribe({
                roomLocalDataSource.insertFeeds(it)
                lastFeedId = it.last().id
                networkState.onNext(NetworkState.COMPLETED)
            }, {
                it.printStackTrace()
                networkState.onNext(NetworkState.ERROR)
            }).bindToLifecycle(this)
    }
}