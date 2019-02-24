package com.droidcon.cleanrepository.data.datasource

import com.droidcon.cleanrepository.data.service.TwitterService
import javax.inject.Inject

class TwitterRemoteDataSource @Inject constructor(twitterService: TwitterService) : TwitterDataSource {
}