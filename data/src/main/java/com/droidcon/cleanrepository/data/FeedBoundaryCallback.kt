package com.droidcon.cleanrepository.data

import androidx.paging.PagedList
import com.droidcon.cleanrepository.data.repository.pagedRepositoryImpl
import com.droidcon.cleanrepository.domain.model.Feed

class FeedBoundaryCallback constructor(private val pagedRepository: pagedRepositoryImpl) :
    PagedList.BoundaryCallback<Feed>() {

    override fun onZeroItemsLoaded() {
        pagedRepository.fetchNextFeed()
    }

    override fun onItemAtEndLoaded(itemAtEnd: Feed) {
        pagedRepository.fetchNextFeed(itemAtEnd.id.split("_")[1].toLong())
    }
}