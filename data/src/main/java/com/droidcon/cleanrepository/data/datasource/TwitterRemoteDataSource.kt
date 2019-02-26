package com.droidcon.cleanrepository.data.datasource

import com.droidcon.cleanrepository.data.mapper.asDomainModel
import com.droidcon.cleanrepository.data.service.TwitterService
import javax.inject.Inject

class TwitterRemoteDataSource @Inject constructor(private val twitterService: TwitterService) : TwitterDataSource {

    private val jakeTwitterName = "jakewharton"
    private val pageSize = 200

    fun getInitialJakeTimeline() = twitterService
        .getUserTimeline(name = jakeTwitterName, totalResults = pageSize)
        .map { list -> list.map { it.asDomainModel() } }

    fun getNextJakeTimeline(lastTweetId: Long) = twitterService
        .getUserTimeline(name = jakeTwitterName, totalResults = pageSize + 1, resultFromId = lastTweetId)
        .map {
            if (it.isNotEmpty()) it.removeAt(0)
            it
        }
        .map { list -> list.map { it.asDomainModel() } }

}