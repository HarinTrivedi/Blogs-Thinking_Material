package com.hlabexamples.blogs.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Blog::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
  abstract fun blogDao(): BlogDao
}