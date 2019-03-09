package com.droidcon.cleanrepository.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.droidcon.cleanrepository.domain.SubscriptionContainer
import com.droidcon.cleanrepository.domain.model.Feed

abstract class PagedRepository : SubscriptionContainer() {

    abstract fun getPagedFeed(): LiveData<PagedList<Feed>>

    abstract fun fetchNextFeed(lastId: Long? = null)
}