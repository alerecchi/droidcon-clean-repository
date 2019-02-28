package com.droidcon.cleanrepository.domain.model

import java.util.*

class Feed(
    val id: String,
    val image: String,
    val name: String,
    val content: String,
    val date: Date,
    val source: FeedSource
)

enum class FeedSource {
    TWITTER,
    GITHUB
}