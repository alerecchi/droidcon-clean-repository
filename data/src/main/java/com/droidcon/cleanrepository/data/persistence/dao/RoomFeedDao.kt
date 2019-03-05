package com.droidcon.cleanrepository.data.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.droidcon.cleanrepository.data.model.local.RoomFeed
import io.reactivex.Flowable

@Dao
interface RoomFeedDao {

    @Query("SELECT * FROM feed ORDER BY date desc")
    fun getFeed(): Flowable<List<RoomFeed>>

    @Query("DELETE FROM feed")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFeed(feed: RoomFeed)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFeedList(data: List<RoomFeed>?)
}