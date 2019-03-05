package com.droidcon.cleanrepository.data.datasource

import com.droidcon.cleanrepository.domain.model.Feed
import io.reactivex.Single

interface GitHubRemoteDataSource {

    fun getPage(pageNumber: Int? = null): Single<List<Feed>>
}