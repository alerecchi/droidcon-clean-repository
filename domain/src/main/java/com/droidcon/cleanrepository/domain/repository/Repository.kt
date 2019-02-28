package com.droidcon.cleanrepository.domain.repository

import com.droidcon.cleanrepository.domain.model.Feed
import io.reactivex.Flowable

interface Repository {

    fun getFeed(): Flowable<List<Feed>>

    fun clearDisposable()
}