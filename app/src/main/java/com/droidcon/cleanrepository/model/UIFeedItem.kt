package com.droidcon.cleanrepository.model

data class UIFeedItem(
    val imageUrl: String,
    val name: String,
    val content: String,
    val date: String,
    val source: Source
) {
    enum class Source(val displayName: String) {
        TWITTER("Twitter"),
        GITHUB("GitHub")
    }
}