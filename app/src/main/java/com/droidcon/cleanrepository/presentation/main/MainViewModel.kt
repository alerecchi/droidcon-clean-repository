package com.droidcon.cleanrepository.presentation.main

import androidx.lifecycle.MutableLiveData
import com.droidcon.cleanrepository.domain.repository.FeedRepository
import com.droidcon.cleanrepository.kx.bindToLifecycle
import com.droidcon.cleanrepository.mapper.asUIModel
import com.droidcon.cleanrepository.model.UIFeedItem
import com.droidcon.cleanrepository.presentation.LifecycleViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: FeedRepository
) : LifecycleViewModel() {

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