package com.droidcon.cleanrepository.data.repository

import com.droidcon.cleanrepository.data.datasource.GitHubRemoteDataSource
import com.droidcon.cleanrepository.data.datasource.TwitterRemoteDataSource
import com.droidcon.cleanrepository.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    twitterRemoteDataSource: TwitterRemoteDataSource,
    gitHubRemoteDataSource: GitHubRemoteDataSource
) : Repository {
}