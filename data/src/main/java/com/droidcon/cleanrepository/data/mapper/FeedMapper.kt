package com.droidcon.cleanrepository.data.mapper

import com.droidcon.cleanrepository.data.model.local.RoomFeed
import com.droidcon.cleanrepository.data.model.remote.GitHubUserEvent
import com.droidcon.cleanrepository.data.model.remote.TwitterUserTimelineItem
import com.droidcon.cleanrepository.domain.model.Feed
import com.droidcon.cleanrepository.domain.model.FeedSource.*
import java.text.SimpleDateFormat
import java.util.*

val TWITTER_DATE_FORMAT = SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH)
val GITHUB_DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)

fun TwitterUserTimelineItem.asDomainModel() = Feed(
    id = "twitter_$id",
    image = user?.profile_image_url_https ?: "",
    name = user?.screen_name ?: "",
    content = text ?: "",
    date = created_at?.let { TWITTER_DATE_FORMAT.parse(it) } ?: Date(0),
    source = TWITTER
)

fun GitHubUserEvent.asDomainModel() = Feed(
    id = "github_$id",
    image = actor?.avatar_url ?: "",
    name = actor?.display_login ?: "",
    content = "$type on ${repo?.name}",
    date = created_at?.let { GITHUB_DATE_FORMAT.parse(it) } ?: Date(0),
    source = GITHUB
)

fun RoomFeed.asDomainModel() = Feed(
    id = feedId,
    image = feedImage,
    name = feedName,
    content = feedContent,
    date = Date(feedDate),
    source = valueOf(this.feedSource)
)

fun Feed.asLocalDataModel() = RoomFeed(
    feedId = id,
    feedImage = image,
    feedName = name,
    feedContent = content,
    feedDate = date.time,
    feedSource = source.name
)