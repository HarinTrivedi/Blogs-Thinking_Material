package com.hlabexamples.blogs.ui.overlaydemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.hlabexamples.blogs.R
import com.hlabexamples.blogs.R.string
import kotlinx.android.synthetic.main.activity_theme_overlay.btnLike

class ThemeOverlayActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {

    //For demo adding overlay manually, you can manage it programmatically as per the requirement
    setOverlays()

    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_theme_overlay)

    btnLike.setOnClickListener {
      MaterialAlertDialogBuilder(this)
        .setTitle(getString(string.thanks))
        .setMessage(getString(string.thanks_for))
        .setNeutralButton(getString(string.close), null)
        .show()
    }
  }

  private fun setOverlays() {
    setTheme(R.style.ThemeOverlay_Color)
    setTheme(R.style.ThemeOverlay_ShapeSize)
    setTheme(R.style.ThemeOverlay_Typography)
  }
}
