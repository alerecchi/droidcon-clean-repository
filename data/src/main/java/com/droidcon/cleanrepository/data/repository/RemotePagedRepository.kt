package com.droidcon.cleanrepository.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.droidcon.cleanrepository.data.datasource.TwitterPagedDataSource
import com.droidcon.cleanrepository.domain.model.Feed
import com.droidcon.cleanrepository.domain.repository.PagedRepository
import javax.inject.Inject

class RemotePagedRepository @Inject constructor(
    private val twitterPagedDataSource: TwitterPagedDataSource
) : PagedRepository() {

    private val config = PagedList.Config.Builder()
        .setPageSize(20)
        .setMaxSize(100)
        .setEnablePlaceholders(false)
        .build()

    override fun <O> getPagedFeed(mapper: (Feed) -> (O)): LiveData<PagedList<O>> =
        object : DataSource.Factory<String, Feed>() {
            override fun create(): DataSource<String, Feed> =
                twitterPagedDataSource.apply {
                    subscriptionContainer = this@RemotePagedRepository
                    networkState = this@RemotePagedRepository.networkState
                }
        }.map { i -> mapper.invoke(i) }
            .toLiveData(config)

}