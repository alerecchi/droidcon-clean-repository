package com.droidcon.cleanrepository.domain.repository

import androidx.paging.DataSource
import com.droidcon.cleanrepository.domain.SubscriptionContainer
import com.droidcon.cleanrepository.domain.model.Feed

abstract class PaginatedFeedRepository : SubscriptionContainer() {

    abstract fun getPaginatedFeed(): DataSource.Factory<Int, Feed>
}