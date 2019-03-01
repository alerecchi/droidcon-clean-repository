package com.droidcon.cleanrepository.data.repository

import com.droidcon.cleanrepository.data.datasource.GitHubRemoteDataSource
import com.droidcon.cleanrepository.data.datasource.LocalDataSource
import com.droidcon.cleanrepository.data.datasource.TwitterRemoteDataSource
import com.droidcon.cleanrepository.data.kx.bindToLifecycle
import com.droidcon.cleanrepository.domain.LifecycleBinder
import com.droidcon.cleanrepository.domain.SimpleLifecycleBinder
import com.droidcon.cleanrepository.domain.model.Feed
import com.droidcon.cleanrepository.domain.repository.Repository
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val twitterRemoteDataSource: TwitterRemoteDataSource,
    private val gitHubRemoteDataSource: GitHubRemoteDataSource,
    private val localDataSource: LocalDataSource
) : Repository, LifecycleBinder by SimpleLifecycleBinder() {

    override fun getFeed(): Flowable<List<Feed>> {
        Single.zip(
            twitterRemoteDataSource.getJakeTimeline(),
            gitHubRemoteDataSource.getPage(),
            BiFunction<List<Feed>, List<Feed>, List<Feed>> { twitterFeeds, githubFeeds ->
                twitterFeeds
                    .plus(githubFeeds)
                    .sortedBy { it.date }
            })
            .subscribe({
                localDataSource.insertFeeds(it)
            }, {
                it.printStackTrace()
            }).bindToLifecycle(this)
        return localDataSource.getFeed()
    }

}