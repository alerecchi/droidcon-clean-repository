package com.droidcon.cleanrepository.data.datasource

import androidx.paging.DataSource
import com.droidcon.cleanrepository.domain.model.Feed
import io.reactivex.Flowable

interface LocalDataSource {

    fun getFeed(): Flowable<List<Feed>>

    fun insertFeeds(list: List<Feed>)

    fun getPagedFeeds(): DataSource.Factory<Int, Feed>
}