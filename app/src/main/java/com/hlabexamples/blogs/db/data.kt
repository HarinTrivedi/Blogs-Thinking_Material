package com.hlabexamples.blogs.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Blog {
  @PrimaryKey(autoGenerate = true) var id: Int = 0
  @ColumnInfo var title: String = ""
  @ColumnInfo var desc: String = ""
  @ColumnInfo var fav: Int = 0
  @ColumnInfo var type: Int = 0
}