package com.droidcon.cleanrepository.data.datasource

import com.droidcon.cleanrepository.domain.model.Feed
import io.reactivex.Single

interface TwitterDataSource {

    fun getTimeline(
        firstTweetId: Long? = null,
        lastTweetId: Long? = null,
        pageSize: Int? = null
    ): Single<List<Feed>>
}