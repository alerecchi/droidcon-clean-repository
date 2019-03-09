package com.droidcon.cleanrepository.data.datasource

import com.droidcon.cleanrepository.data.BuildConfig
import com.droidcon.cleanrepository.data.mapper.asDomainModel
import com.droidcon.cleanrepository.data.service.TwitterService
import com.droidcon.cleanrepository.domain.model.Feed
import io.reactivex.Single
import javax.inject.Inject

class TwitterDataSourceImpl @Inject constructor(private val twitterService: TwitterService) : TwitterDataSource {

    private val jakeTwitterName = "jakewharton"
    private val pageSize = 200

    override fun getTimeline(lastTweetId: Long?): Single<List<Feed>> {
        return twitterService
            .getUserTimeline(
                auth = "Bearer ${BuildConfig.TWITTER_BEARER_TOKEN}",
                name = jakeTwitterName,
                totalResults = pageSize,
                resultFromId = lastTweetId
            )
            .map {
                if (lastTweetId != null && it.isNotEmpty()) it.subList(1, it.size)
                else it
            }
            .map { list -> list.map { it.asDomainModel() } }
    }

}