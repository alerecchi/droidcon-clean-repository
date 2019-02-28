package com.droidcon.cleanrepository.data.datasource

import com.droidcon.cleanrepository.data.mapper.asDomainModel
import com.droidcon.cleanrepository.data.service.GithubService
import com.droidcon.cleanrepository.domain.model.Feed
import io.reactivex.Single
import javax.inject.Inject

class GitHubRemoteDataSource @Inject constructor(private val githubService: GithubService) : GitHubDataSource {

    private val userId = "66577"

    fun getPage(pageNumber: Int? = null): Single<List<Feed>> {
        return githubService
            .getUserTimeline(
                user = userId,
                page = pageNumber
            ).map { list -> list.map { it.asDomainModel() } }
    }
}