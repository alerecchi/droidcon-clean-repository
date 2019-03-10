package com.droidcon.cleanrepository.data.service

import com.droidcon.cleanrepository.data.model.remote.GitHubUserEvent
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("https://api.github.com/user/{user}/events")
    fun getUserEvents(
        @Path("user")
        user: String,
        @Query("page")
        page: Int? = null
    ): Single<List<GitHubUserEvent>>
}