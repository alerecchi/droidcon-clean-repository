package com.droidcon.cleanrepository.presentation.paginatedsource

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.droidcon.cleanrepository.data.repository.PaginatedRepository
import com.droidcon.cleanrepository.mapper.asUIModel
import com.droidcon.cleanrepository.model.UIFeedItem
import com.droidcon.cleanrepository.presentation.base.LifecycleViewModel
import javax.inject.Inject

class PaginatedViewModel @Inject constructor(paginatedRepository: PaginatedRepository) : LifecycleViewModel() {

    private val config = PagedList.Config.Builder()
        .setPageSize(20)
        .setEnablePlaceholders(false)
        .build()

    private val uiDataFactory = paginatedRepository.getPaginatedFeed().map { it.asUIModel() }

    val paginatedFeed: LiveData<PagedList<UIFeedItem>> = LivePagedListBuilder(uiDataFactory, config).build()
}