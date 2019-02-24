package com.droidcon.persistence

import androidx.room.*
import com.droidcon.RoomFeed
import io.reactivex.Flowable

@Dao
interface RoomFeedDao {

    @Query("SELECT * FROM feed " +
            "ORDER BY date desc")
    fun getFeed(): Flowable<MutableList<RoomFeed>>

    @Delete
    fun deleteMessage(feed: RoomFeed)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessage(feed: RoomFeed)
}