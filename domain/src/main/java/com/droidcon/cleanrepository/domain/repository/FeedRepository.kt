package com.droidcon.cleanrepository.domain.repository

import com.droidcon.cleanrepository.domain.SubscriptionContainer
import com.droidcon.cleanrepository.domain.model.Feed
import com.droidcon.cleanrepository.domain.model.NetworkState
import io.reactivex.Flowable
import io.reactivex.subjects.PublishSubject

abstract class FeedRepository : SubscriptionContainer() {

    abstract val networkState: PublishSubject<NetworkState>
    abstract fun getFeed(): Flowable<List<Feed>>
    abstract fun refreshFeed()
}