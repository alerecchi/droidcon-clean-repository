package com.droidcon.cleanrepository.presentation.main

import androidx.lifecycle.MutableLiveData
import com.droidcon.cleanrepository.domain.repository.Repository
import com.droidcon.cleanrepository.kx.bindToLifecycle
import com.droidcon.cleanrepository.mapper.asUIModel
import com.droidcon.cleanrepository.model.UIFeedItem
import com.droidcon.cleanrepository.presentation.LifecycleViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: Repository
) : LifecycleViewModel() {

    val feedList = MutableLiveData<List<UIFeedItem>>()

    fun getFeeds() {
        repository.getFeed()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { list ->
                    feedList.value = list.map { it.asUIModel() }
                },
                {

                })
            .bindToLifecycle(this)
    }

    override fun onCleared() {
        super.onCleared()
        repository.clearDisposable()
    }
}
