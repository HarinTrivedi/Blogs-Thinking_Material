package com.hlabexamples.blogs.ui.launch

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.hlabexamples.blogs.R
import com.hlabexamples.blogs.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_launcher.ivLogo

class LaunchActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_launcher)

    Glide.with(this)
      .asGif()
      .load(R.drawable.logo)
      .into(ivLogo)

    Handler().postDelayed({
      startActivity(Intent(this@LaunchActivity, MainActivity::class.java))
      finish()
    }, 3400)
  }

  override fun onBackPressed() {
  }
}
