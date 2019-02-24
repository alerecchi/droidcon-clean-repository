package com.droidcon.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.droidcon.RoomFeed

@Database(
    entities = [RoomFeed::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun feedDao(): RoomFeedDao
}