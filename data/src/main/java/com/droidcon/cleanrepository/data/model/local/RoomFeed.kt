package com.droidcon.cleanrepository.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feed")
data class RoomFeed(
    @PrimaryKey
    val feedId: String,
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