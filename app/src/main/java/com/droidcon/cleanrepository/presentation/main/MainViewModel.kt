package com.droidcon.cleanrepository.presentation.main

import com.droidcon.cleanrepository.data.GithubService
import com.droidcon.cleanrepository.data.TwitterService
import com.droidcon.cleanrepository.presentation.LifecycleViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(
    var githubService: GithubService,
    var twitterService: TwitterService
) : LifecycleViewModel()
