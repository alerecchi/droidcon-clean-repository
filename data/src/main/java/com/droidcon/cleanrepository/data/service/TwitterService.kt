package com.droidcon.cleanrepository.data.service

import com.droidcon.cleanrepository.data.model.remote.TwitterUserTimelineItem
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface TwitterService {

    @GET("https://api.twitter.com/1.1/statuses/user_timeline.json")
    fun getUserTimeline(
        @Header("Authorization")
        auth: String? = null,
        @Query("screen_name")
        name: String? = null,
        @Query("count")
        totalResults: Int? = null,
        @Query("max_id")
        resultFromId: Long? = null
    ): Single<List<TwitterUserTimelineItem>>

}