package com.droidcon.cleanrepository.domain.repository

import com.droidcon.cleanrepository.domain.LifecycleBinder
import com.droidcon.cleanrepository.domain.model.Feed
import io.reactivex.Flowable

interface Repository : LifecycleBinder {

    fun getFeed(): Flowable<List<Feed>>

}