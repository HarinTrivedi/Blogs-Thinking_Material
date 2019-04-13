package com.hlabexamples.blogs.utils

import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.shape.CutCornerTreatment
import com.google.android.material.shape.MaterialShapeDrawable

fun setUpBottomAppBarShapeAppearance(
  fab: FloatingActionButton,
  bar: BottomAppBar
) {
  val fabShapeAppearanceModel = fab.shapeAppearance
  val cutCornersFab = fabShapeAppearanceModel.bottomLeftCorner is CutCornerTreatment && fabShapeAppearanceModel.bottomRightCorner is CutCornerTreatment

  val topEdge = if (cutCornersFab)
    BottomAppBarCutCornersTopEdge(
      bar.fabCradleMargin,
      bar.fabCradleRoundedCornerRadius,
      bar.cradleVerticalOffset
    )
  else
    BottomAppBarTopEdgeTreatment(
      bar.fabCradleMargin,
      bar.fabCradleRoundedCornerRadius,
      bar.cradleVerticalOffset
    )

  val babBackground = bar.background as MaterialShapeDrawable
  babBackground.shapeAppearanceModel.topEdge = topEdge
  babBackground.invalidateSelf()
}

enum class Type(var value: Int) {
  TRAVEL(1),
  TECH(2),
  BUSINESS(3),
  ART(4)
}
