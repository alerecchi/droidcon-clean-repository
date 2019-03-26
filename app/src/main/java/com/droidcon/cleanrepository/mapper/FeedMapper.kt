package com.droidcon.cleanrepository.mapper

import com.droidcon.cleanrepository.domain.model.Feed
import com.droidcon.cleanrepository.domain.model.FeedSource.TWITTER
import com.droidcon.cleanrepository.model.UIFeedItem
import java.text.SimpleDateFormat
import java.util.*

fun Feed.asUIModel() = UIFeedItem(
    id = id,
    imageUrl = image,
    name = name,
    content = content,
    date = date.let { UI_DATE_FORMAT.format(it) } ?: "",
    source = if (source == TWITTER) UIFeedItem.Source.TWITTER else UIFeedItem.Source.GITHUB
)

val UI_DATE_FORMAT = SimpleDateFormat("'on' EEEE, MMM d, YYY", Locale.ENGLISH)