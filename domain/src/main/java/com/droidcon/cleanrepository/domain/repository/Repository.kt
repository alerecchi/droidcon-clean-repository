package com.droidcon.cleanrepository.domain.repository

import com.droidcon.cleanrepository.domain.model.Feed
import io.reactivex.Single

interface Repository {

    fun getTwitterFeed(): Single<List<Feed>>

}