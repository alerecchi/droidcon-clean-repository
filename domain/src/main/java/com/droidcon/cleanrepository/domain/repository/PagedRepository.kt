package com.droidcon.cleanrepository.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.droidcon.cleanrepository.domain.SubscriptionContainer
import com.droidcon.cleanrepository.domain.model.Feed
import com.droidcon.cleanrepository.domain.model.NetworkState
import io.reactivex.subjects.PublishSubject

abstract class PagedRepository : SubscriptionContainer() {

    abstract val networkState: PublishSubject<NetworkState>
    abstract fun <O> getPagedFeed(mapper: (Feed) -> O): LiveData<PagedList<O>>
    abstract fun fetchNextFeed(lastId: Long? = null)

}