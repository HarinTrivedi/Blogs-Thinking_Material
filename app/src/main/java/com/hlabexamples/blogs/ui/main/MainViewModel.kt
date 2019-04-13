package com.hlabexamples.blogs.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hlabexamples.blogs.App
import com.hlabexamples.blogs.db.Blog
import java.util.concurrent.Executors

class MainViewModel : ViewModel() {

  var blogLiveData = MutableLiveData<List<Blog>>()
  var blogData = MutableLiveData<Blog>()
  var blogInsertData = MutableLiveData<Boolean>()
  var blogFavData = MutableLiveData<Boolean>()

  fun loadData(isFav: Boolean) {

    Executors.newSingleThreadExecutor().execute {
      App.db.let {
        if (isFav) {
          blogLiveData.postValue(it.blogDao().getFavBlogs())
        } else {
          blogLiveData.postValue(it.blogDao().getAllBlogs())
        }
      }
    }
  }

  fun getBlog(id: Int) {
    Executors.newSingleThreadExecutor().execute {
      blogData.postValue(App.db.blogDao().getSingle(id))
    }
  }

  fun insertBlog(newBlog: Blog) {
    Executors.newSingleThreadExecutor().execute {
      App.db.blogDao().insert(newBlog)
      blogInsertData.postValue(true)
    }
  }

  fun updateBlog(blog: Blog) {
    Executors.newSingleThreadExecutor().execute {
      App.db.blogDao().update(blog)
      blogFavData.postValue(true)
    }
  }
}