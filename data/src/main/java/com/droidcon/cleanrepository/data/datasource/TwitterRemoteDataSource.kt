package com.droidcon.cleanrepository.data.datasource

import com.droidcon.cleanrepository.data.mapper.asDomainModel
import com.droidcon.cleanrepository.data.service.TwitterService
import com.droidcon.cleanrepository.domain.model.Feed
import io.reactivex.Single
import javax.inject.Inject

class TwitterRemoteDataSource @Inject constructor(private val twitterService: TwitterService) : TwitterDataSource {

    private val jakeTwitterName = "jakewharton"
    private val pageSize = 200

    fun getInitialJakeTimeline(): Single<List<Feed>> {
        return twitterService
            .getUserTimeline(
                auth = "Bearer ***",
                name = jakeTwitterName,
                totalResults = pageSize
            )
            .map { list -> list.map { it.asDomainModel() } }
    }

    fun getNextJakeTimeline(lastTweetId: Long): Single<List<Feed>> {
        return twitterService
            .getUserTimeline(
                auth = "Bearer ***",
                name = jakeTwitterName,
                totalResults = pageSize,
                resultFromId = lastTweetId
            )
            .map {
                if (it.isNotEmpty()) it.subList(1, it.size)
                else it
            }
            .map { list -> list.map { it.asDomainModel() } }
    }

    /*fun getJakeTimeline(lastTweetId: Long? = null): Single<List<Feed>> {
        return twitterService
            .getUserTimeline(
                auth = "Bearer ***",
                name = jakeTwitterName,
                totalResults = pageSize,
                resultFromId = lastTweetId
            )
            .map {
                if (it.isNotEmpty()) it.subList(1, it.size)
                else it
            }
            .map { list -> list.map { it.asDomainModel() } }
    }*/

}