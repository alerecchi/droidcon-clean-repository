package com.droidcon.cleanrepository.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.droidcon.cleanrepository.data.datasource.LocalDataSource
import com.droidcon.cleanrepository.data.datasource.TwitterDataSource
import com.droidcon.cleanrepository.data.kx.bindToLifecycle
import com.droidcon.cleanrepository.domain.model.Feed
import com.droidcon.cleanrepository.domain.repository.PagedRepository
import javax.inject.Inject

class PagedRepositoryImpl @Inject constructor(
    private val twitterRemoteDataSource: TwitterDataSource,
    private val roomLocalDataSource: LocalDataSource
) : PagedRepository() {

    private val config = PagedList.Config.Builder()
        .setPageSize(20)
        .setEnablePlaceholders(false)
        .build()

    override fun getPagedFeed(): LiveData<PagedList<Feed>> {
        return roomLocalDataSource.getPagedFeeds().toLiveData(
            config,
            boundaryCallback = object : PagedList.BoundaryCallback<Feed>() {
                override fun onZeroItemsLoaded() = fetchNextFeed()

                override fun onItemAtEndLoaded(itemAtEnd: Feed) =
                    fetchNextFeed(itemAtEnd.id.split("_")[1].toLong())
            }
        )
    }

    override fun fetchNextFeed(lastId: Long?) {
        twitterRemoteDataSource.getTimeline(lastId)
            .map { list -> list.sortedBy { it.date } }
            .subscribe({
                roomLocalDataSource.insertFeeds(it)
            }, {
                it.printStackTrace()
            }).bindToLifecycle(this)
    }
}