package com.hlabexamples.blogs.utils

import android.app.Activity
import androidx.annotation.StyleRes
import java.util.Arrays

object ThemeUtils {

  @StyleRes @get:StyleRes
  var themeOverlays = IntArray(0)
    private set

  fun setThemeOverlays(activity: Activity, @StyleRes vararg themeOverlays: Int) {
    if (!Arrays.equals(ThemeUtils.themeOverlays, themeOverlays)) {
      ThemeUtils.themeOverlays = themeOverlays
      activity.recreate()
    }
  }

  fun applyThemeOverlays(activity: Activity) {
    for (themeOverlay in themeOverlays) {
      activity.setTheme(themeOverlay)
    }
  }
}
