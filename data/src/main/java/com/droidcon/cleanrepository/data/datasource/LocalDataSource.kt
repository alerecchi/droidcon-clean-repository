package com.droidcon.cleanrepository.data.datasource

import com.droidcon.cleanrepository.data.mapper.asDomainModel
import com.droidcon.cleanrepository.data.mapper.asLocalDataModel
import com.droidcon.cleanrepository.data.persistence.dao.RoomFeedDao
import com.droidcon.cleanrepository.domain.model.Feed
import io.reactivex.Flowable
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val feedDao: RoomFeedDao) {

    fun getFeed(): Flowable<List<Feed>> {
        return feedDao
            .getFeed()
            .map { list -> list.map { it.asDomainModel() } }
    }

    fun insertFeeds(list: List<Feed>) {
        feedDao.insertFeedList(list.map { it.asLocalDataModel() })
    }
}