package com.droidcon.cleanrepository.presentation.pagedsource

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.paging.PagedList
import com.droidcon.cleanrepository.domain.model.NetworkState
import com.droidcon.cleanrepository.domain.repository.PagedRepository
import com.droidcon.cleanrepository.mapper.asUIModel
import com.droidcon.cleanrepository.model.UIFeedItem
import com.droidcon.cleanrepository.presentation.base.LifecycleViewModel
import io.reactivex.BackpressureStrategy

abstract class PagedViewModel constructor(pagedRepository: PagedRepository) : LifecycleViewModel() {

    init {
        addSubscriptionContainer(pagedRepository)
    }

    val networkState: LiveData<NetworkState> = LiveDataReactiveStreams
        .fromPublisher(pagedRepository.networkState.toFlowable(BackpressureStrategy.BUFFER))
    val pagedFeed: LiveData<PagedList<UIFeedItem>> = pagedRepository.getPagedFeed { it.asUIModel() }
}