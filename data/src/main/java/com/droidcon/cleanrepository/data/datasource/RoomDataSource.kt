package com.droidcon.cleanrepository.data.datasource

import androidx.paging.DataSource
import com.droidcon.cleanrepository.data.mapper.asDomainModel
import com.droidcon.cleanrepository.data.mapper.asLocalDataModel
import com.droidcon.cleanrepository.data.persistence.dao.RoomFeedDao
import com.droidcon.cleanrepository.domain.model.Feed
import io.reactivex.Flowable
import javax.inject.Inject

class RoomDataSource @Inject constructor(private val feedDao: RoomFeedDao) : LocalDataSource {

    override fun getFeed(): Flowable<List<Feed>> {
        return feedDao
            .getFeed()
            .map { list -> list.map { it.asDomainModel() } }
    }

    override fun insertFeeds(list: List<Feed>) {
        feedDao.insertFeedList(list.map { it.asLocalDataModel() })
    }

    override fun getPaginatedFeeds(): DataSource.Factory<Int, Feed> {
        return feedDao.getAllPaged().map { it.asDomainModel() }
    }
}