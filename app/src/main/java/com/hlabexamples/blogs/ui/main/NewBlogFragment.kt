package com.hlabexamples.blogs.ui.main

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.hlabexamples.blogs.R
import com.hlabexamples.blogs.db.Blog
import com.hlabexamples.blogs.ui.BaseFragment
import com.hlabexamples.blogs.utils.Type
import kotlinx.android.synthetic.main.fragment_new_blog.coordinatorLayout
import kotlinx.android.synthetic.main.fragment_new_blog.edtDescription
import kotlinx.android.synthetic.main.fragment_new_blog.edtTitle
import kotlinx.android.synthetic.main.fragment_new_blog.tilDescription
import kotlinx.android.synthetic.main.fragment_new_blog.tilTitle
import kotlinx.android.synthetic.main.fragment_new_blog.toggleGroupType
import kotlinx.android.synthetic.main.fragment_new_blog.toolbar

class NewBlogFragment : BaseFragment<MainViewModel>() {
  private var type = Type.TRAVEL

  override fun layoutResId() = R.layout.fragment_new_blog

  override fun attachedViewModel() = MainViewModel::class.java

  override fun initialize() {
    toolbar.setNavigationOnClickListener {
      findNavController().navigateUp()
    }
    toolbar.setOnMenuItemClickListener {

      if (it.itemId == R.id.menu_save) {
        tilTitle.error = ""
        tilDescription.error = ""

        when {
          edtTitle.text.toString().isEmpty() -> tilTitle.error = "Please enter title"
          edtTitle.text.toString().isEmpty() -> tilDescription.error = "Please enter description"
          else -> {
            val newBlog = Blog()
            newBlog.title = edtTitle.text.toString()
            newBlog.desc = edtDescription.text.toString()
            newBlog.type = type.value

            viewMode.insertBlog(newBlog)

            return@setOnMenuItemClickListener true
          }
        }
      }
      return@setOnMenuItemClickListener false
    }
    toggleGroupType.addOnButtonCheckedListener { _, checkedId, selected ->
      if (selected) {
        when (checkedId) {
          R.id.btnTravel -> type = Type.TRAVEL
          R.id.btnTech -> type = Type.TECH
          R.id.btnBusiness -> type = Type.BUSINESS
          R.id.btnArt -> type = Type.ART
        }
      }
    }

    viewMode.blogInsertData.observe(this, Observer {
      if (it) {
        findNavController().navigateUp()
        Snackbar.make(coordinatorLayout, "Success!!", Snackbar.LENGTH_SHORT).show()
      }
    })
  }

//  override fun onBackPressed() {
//    if (fabMenu.isShowing)
//      fabMenu.closeMenu()
//    else
//      super.onBackPressed()
//  }
}
