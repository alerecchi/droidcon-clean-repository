package com.droidcon.cleanrepository.domain.repository

import com.droidcon.cleanrepository.domain.SubscriptionContainer
import com.droidcon.cleanrepository.domain.model.Feed
import io.reactivex.Flowable

abstract class FeedRepository : SubscriptionContainer() {

    abstract fun getFeed(): Flowable<List<Feed>>
    abstract fun refreshFeed()
}