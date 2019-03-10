package com.droidcon.cleanrepository.data.datasource

import com.droidcon.cleanrepository.data.BuildConfig
import com.droidcon.cleanrepository.data.mapper.asDomainModel
import com.droidcon.cleanrepository.data.service.TwitterService
import com.droidcon.cleanrepository.domain.model.Feed
import io.reactivex.Single
import javax.inject.Inject

open class TwitterDataSourceImpl @Inject constructor(private val twitterService: TwitterService) : TwitterDataSource {

    private val jakeTwitterName = "jakewharton"

    companion object {
        private const val DEFAULT_PAGE_SIZE = 200
    }

    override fun getTimeline(firstTweetId: Long?, lastTweetId: Long?, pageSize: Int?): Single<List<Feed>> {
        return twitterService
            .getUserTimeline(
                auth = "Bearer ${BuildConfig.TWITTER_BEARER_TOKEN}",
                name = jakeTwitterName,
                totalResults =
                if (lastTweetId != null)
                    pageSize?.plus(1) ?: DEFAULT_PAGE_SIZE + 1
                else
                    pageSize ?: DEFAULT_PAGE_SIZE,
                resultFromId = lastTweetId,
                resultBeforeId = firstTweetId
            )
            .map {
                if (lastTweetId != null && it.isNotEmpty()) it.subList(1, it.size)
                else it
            }
            .map { list -> list.map { it.asDomainModel() } }
    }

}