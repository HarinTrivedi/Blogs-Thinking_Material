package com.hlabexamples.blogs

import android.app.Application
import androidx.room.Room
import com.hlabexamples.blogs.db.AppDatabase

class App : Application() {

  override fun onCreate() {
    super.onCreate()
    db = Room.inMemoryDatabaseBuilder(this, AppDatabase::class.java).build()
  }

  companion object {
    lateinit var db: AppDatabase
  }
}