package com.droidcon.cleanrepository.data.datasource

import com.droidcon.cleanrepository.domain.model.Feed
import io.reactivex.Single

interface TwitterDataSource {

    fun getTimeline(lastTweetId: Long? = null): Single<List<Feed>>
}