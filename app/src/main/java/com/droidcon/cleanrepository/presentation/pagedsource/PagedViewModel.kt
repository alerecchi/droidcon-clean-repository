package com.droidcon.cleanrepository.presentation.pagedsource

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.droidcon.cleanrepository.domain.repository.PagedRepository
import com.droidcon.cleanrepository.mapper.asUIModel
import com.droidcon.cleanrepository.model.UIFeedItem
import com.droidcon.cleanrepository.presentation.base.LifecycleViewModel
import javax.inject.Inject

class PagedViewModel @Inject constructor(pagedRepository: PagedRepository) : LifecycleViewModel() {

    init {
        addSubscriptionContainer(pagedRepository)
    }

    val pagedFeed: LiveData<PagedList<UIFeedItem>> = pagedRepository.getPagedFeed { it.asUIModel() }
}