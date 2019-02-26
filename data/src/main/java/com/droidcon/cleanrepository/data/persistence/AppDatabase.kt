package com.droidcon.cleanrepository.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.droidcon.cleanrepository.data.model.local.RoomFeed
import com.droidcon.cleanrepository.data.persistence.dao.RoomFeedDao

@Database(
    entities = [RoomFeed::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun feedDao(): RoomFeedDao

}