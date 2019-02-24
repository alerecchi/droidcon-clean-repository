package com.droidcon.cleanrepository.data.persistence.dao

import androidx.room.*
import com.droidcon.cleanrepository.data.model.local.RoomFeed
import io.reactivex.Flowable

@Dao
interface RoomFeedDao {

    @Query("SELECT * FROM feed ORDER BY date desc")
    fun getFeed(): Flowable<MutableList<RoomFeed>>

    @Delete
    fun deleteMessage(feed: RoomFeed)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessage(feed: RoomFeed)
}