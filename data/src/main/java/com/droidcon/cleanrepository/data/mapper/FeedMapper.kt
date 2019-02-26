package com.droidcon.cleanrepository.data.mapper

import com.droidcon.cleanrepository.data.model.local.RoomFeed
import com.droidcon.cleanrepository.data.model.remote.GitHubUserEvent
import com.droidcon.cleanrepository.data.model.remote.TwitterUserTimelineItem
import com.droidcon.cleanrepository.domain.model.Feed
import com.droidcon.cleanrepository.domain.model.FeedSource.GITHUB
import com.droidcon.cleanrepository.domain.model.FeedSource.TWITTER
import java.text.SimpleDateFormat
import java.util.*

fun TwitterUserTimelineItem.asDomainModel() = Feed(
    image = user?.profile_image_url_https ?: "",
    name = user?.screen_name ?: "",
    content = text ?: "",
    date = created_at?.let { TWITTER_DATE_FORMAT.parse(it) },
    source = TWITTER
)

fun GitHubUserEvent.asDomainModel() = Feed(
    image = actor?.avatar_url ?: "",
    name = actor?.display_login ?: "",
    content = "$type on ${repo?.name}",
    date = created_at?.let { GITHUB_DATE_FORMAT.parse(it) },
    source = GITHUB
)

fun GitHubUserEvent.asLocalDataModel() = RoomFeed(
    feedImage = actor?.avatar_url ?: "",
    feedName = actor?.display_login ?: "",
    feedContent = "$type on ${repo?.name}",
    feedDate = created_at?.let { GITHUB_DATE_FORMAT.parse(it).time },
    feedSource = GITHUB.name
)

fun TwitterUserTimelineItem.asLocalDataModel() = RoomFeed(
    feedImage = user?.profile_image_url_https ?: "",
    feedName = user?.screen_name ?: "",
    feedContent = text ?: "",
    feedDate = created_at?.let { TWITTER_DATE_FORMAT.parse(it).time },
    feedSource = TWITTER.name
)

val TWITTER_DATE_FORMAT = SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH)
val GITHUB_DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)