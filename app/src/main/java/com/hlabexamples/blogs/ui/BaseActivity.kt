package com.hlabexamples.blogs.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hlabexamples.blogs.R

abstract class BaseActivity : AppCompatActivity() {

  companion object {
    var theme: Int = R.style.AppTheme
  }

  override fun onCreate(savedInstanceState: Bundle?) {

    //This is only required when changing theme dynamically from app
    //Remove it if applying theme in Manifest
    setTheme(BaseActivity.theme)

    super.onCreate(savedInstanceState)
    setContentView(layoutResId())
    initialize()
  }

  protected abstract fun layoutResId(): Int

  protected abstract fun initialize()
}
