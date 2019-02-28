package com.droidcon.cleanrepository.data.repository

import androidx.lifecycle.LifecycleOwner
import com.droidcon.cleanrepository.data.datasource.GitHubRemoteDataSource
import com.droidcon.cleanrepository.data.datasource.LocalDataSource
import com.droidcon.cleanrepository.data.datasource.TwitterRemoteDataSource
import com.droidcon.cleanrepository.data.kx.bindToLifecycle
import com.droidcon.cleanrepository.domain.model.Feed
import com.droidcon.cleanrepository.domain.repository.Repository
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val twitterRemoteDataSource: TwitterRemoteDataSource,
    private val gitHubRemoteDataSource: GitHubRemoteDataSource,
    private val localDataSource: LocalDataSource
) : Repository {

    lateinit var lifecycleOwner: LifecycleOwner

    override fun getFeed(): Flowable<List<Feed>> {
        twitterRemoteDataSource.getJakeTimeline()
            .subscribe({
                localDataSource.insertFeeds(it)
            }, {

            }).bindToLifecycle(lifecycleOwner)
        return localDataSource.getFeed()
    }

    override fun bindToLifecycle(lifecycleOwner: LifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner
    }
}