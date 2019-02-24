package com.droidcon.data

data class UIFeedItem(
    val imageUrl: String,
    val name: String,
    val activity: String,
    val date: String,
    val logo: String //TODO: enum
)