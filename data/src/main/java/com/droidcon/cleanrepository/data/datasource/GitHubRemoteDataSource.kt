package com.droidcon.cleanrepository.data.datasource

import com.droidcon.cleanrepository.data.service.GithubService
import javax.inject.Inject

class GitHubRemoteDataSource @Inject constructor(githubService: GithubService) : GitHubDataSource {
}