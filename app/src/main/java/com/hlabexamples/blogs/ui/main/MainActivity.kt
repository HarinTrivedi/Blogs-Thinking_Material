package com.hlabexamples.blogs.ui.main

import androidx.navigation.findNavController
import com.hlabexamples.blogs.R
import com.hlabexamples.blogs.ui.BaseActivity

class MainActivity : BaseActivity() {
  override fun layoutResId() = R.layout.activity_main

  override fun initialize() {

  }

  override fun onBackPressed() {
    if (!findNavController(R.id.nav_host_fragment).popBackStack())
      super.onBackPressed()
  }
}

