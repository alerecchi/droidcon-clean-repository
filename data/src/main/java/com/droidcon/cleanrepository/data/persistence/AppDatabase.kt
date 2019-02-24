package com.droidcon.cleanrepository.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.droidcon.cleanrepository.data.model.local.RoomFeed
import com.droidcon.cleanrepository.data.persistence.dao.RoomFeedDao

@Database(
    entities = [RoomFeed::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun feedDao(): RoomFeedDao
}