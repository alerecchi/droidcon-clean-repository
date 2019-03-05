package com.droidcon.cleanrepository.presentation.base

import androidx.lifecycle.MutableLiveData
import com.droidcon.cleanrepository.domain.repository.FeedRepository
import com.droidcon.cleanrepository.kx.bindToLifecycle
import com.droidcon.cleanrepository.mapper.asUIModel
import com.droidcon.cleanrepository.model.UIFeedItem
import io.reactivex.android.schedulers.AndroidSchedulers

abstract class BaseViewModel constructor(private val repository: FeedRepository) : LifecycleViewModel() {

    init {
        getFeeds()
        addSubscriptionContainer(repository)
    }

    val feedList = MutableLiveData<List<UIFeedItem>>()

    private fun getFeeds() {
        repository.getFeed()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { list -> feedList.value = list.map { it.asUIModel() } },
                {})
            .bindToLifecycle(this)
    }

    fun refreshFeeds() {
        repository.refreshFeed()
    }


}