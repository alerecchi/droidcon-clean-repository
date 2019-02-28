package com.droidcon.cleanrepository.data.repository

import com.droidcon.cleanrepository.data.datasource.GitHubRemoteDataSource
import com.droidcon.cleanrepository.data.datasource.LocalDataSource
import com.droidcon.cleanrepository.data.datasource.TwitterRemoteDataSource
import com.droidcon.cleanrepository.domain.model.Feed
import com.droidcon.cleanrepository.domain.repository.Repository
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val twitterRemoteDataSource: TwitterRemoteDataSource,
    private val gitHubRemoteDataSource: GitHubRemoteDataSource,
    private val localDatasource: LocalDataSource
) : Repository {

    private val compositeDisposable = CompositeDisposable()

    override fun clearDisposable() {
        compositeDisposable.clear()
    }

    override fun getFeed(): Flowable<List<Feed>> {
        refreshPersistentData()
        return localDatasource.getFeed()
    }

    private fun refreshPersistentData() {
        compositeDisposable.add(twitterRemoteDataSource
            .getInitialJakeTimeline()
            .subscribe({
                localDatasource.insertFeeds(it)
            }, {})
        )
    }

}