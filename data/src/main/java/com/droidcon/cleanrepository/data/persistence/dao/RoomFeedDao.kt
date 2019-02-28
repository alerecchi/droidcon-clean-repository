package com.droidcon.cleanrepository.data.persistence.dao

import androidx.room.*
import com.droidcon.cleanrepository.data.model.local.RoomFeed
import io.reactivex.Flowable

@Dao
interface RoomFeedDao {

    @Query("SELECT * FROM feed ORDER BY date desc")
    fun getFeed(): Flowable<List<RoomFeed>>

    @Delete
    fun deleteFeed(feed: RoomFeed)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFeed(feed: RoomFeed)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFeedList(data: List<RoomFeed>?)
}