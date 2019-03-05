package com.droidcon.cleanrepository.data.datasource

import com.droidcon.cleanrepository.domain.model.Feed
import io.reactivex.Flowable

interface LocalDataSource {

    fun getFeed(): Flowable<List<Feed>>

    fun insertFeeds(list: List<Feed>)
}