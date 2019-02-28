package com.droidcon.cleanrepository.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feed")
class RoomFeed(
    @PrimaryKey(autoGenerate = true)
    val feedId: Int = 0,
    @ColumnInfo(name = "image")
    val feedImage: String,
    @ColumnInfo(name = "name")
    val feedName: String,
    @ColumnInfo(name = "content")
    val feedContent: String,
    @ColumnInfo(name = "date")
    val feedDate: Long,
    @ColumnInfo(name = "source")
    val feedSource: String
)