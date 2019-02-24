package com.droidcon.data

data class TwitterUserActivity(
    val created_at: String?,
    val text: String?,
    val user: User
)

data class User(
    val screen_name: String?,
    val profile_image_url_https: String?
)