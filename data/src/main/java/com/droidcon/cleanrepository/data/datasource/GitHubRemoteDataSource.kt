package com.droidcon.cleanrepository.data.datasource

import com.droidcon.cleanrepository.data.service.GithubService
import javax.inject.Inject

class GitHubRemoteDataSource @Inject constructor(private val githubService: GithubService) : GitHubDataSource {


}