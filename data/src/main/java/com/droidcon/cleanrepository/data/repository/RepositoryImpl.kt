package com.droidcon.cleanrepository.data.repository

import com.droidcon.cleanrepository.data.datasource.GitHubRemoteDataSource
import com.droidcon.cleanrepository.data.datasource.TwitterRemoteDataSource
import com.droidcon.cleanrepository.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val twitterRemoteDataSource: TwitterRemoteDataSource,
    val gitHubRemoteDataSource: GitHubRemoteDataSource
) : Repository {

    override fun getTwitterFeed() = twitterRemoteDataSource.getInitialJakeTimeline()

}