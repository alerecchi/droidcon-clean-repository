package com.droidcon

data class GitHubUserEvent(
    val type: String?,
    val actor: Actor?,
    val repo: Repo,
    val created_at: String?
)

data class Actor(
    val display_login: String?,
    val avatar_url: String?
)

data class Repo(
    val name: String?,
    val url: String?
)