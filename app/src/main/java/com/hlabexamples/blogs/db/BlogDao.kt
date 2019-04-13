package com.hlabexamples.blogs.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface BlogDao {
  @Query("SELECT * FROM Blog")
  fun getAllBlogs(): List<Blog>

  @Query("SELECT * FROM Blog WHERE fav == 1")
  fun getFavBlogs(): List<Blog>

  @Query("SELECT * FROM Blog WHERE id == :id")
  fun getSingle(id: Int): Blog

  @Update
  fun update(blog: Blog)

  @Insert
  fun insert(blog: Blog)

  @Delete
  fun delete(blog: Blog)
}