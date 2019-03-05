package com.droidcon.cleanrepository.data.datasource

import com.droidcon.cleanrepository.domain.model.Feed
import io.reactivex.Single

interface TwitterRemoteDataSource {

    fun getTimeline(lastTweetId: Long? = null): Single<List<Feed>>
}