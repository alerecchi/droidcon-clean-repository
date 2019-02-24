package com.droidcon.cleanrepository.model

data class UIFeedItem(
    val imageUrl: String,
    val name: String,
    val activity: String,
    val date: String,
    val source: Source
) {
    enum class Source { TWITTER, GITHUB }
}