package com.hlabexamples.blogs.ui.main

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.hlabexamples.blogs.R
import com.hlabexamples.blogs.db.Blog
import com.hlabexamples.blogs.ui.BaseFragment
import com.hlabexamples.blogs.utils.Type
import kotlinx.android.synthetic.main.fragment_blog_detail.coordinatorLayout
import kotlinx.android.synthetic.main.fragment_blog_detail.fabLike
import kotlinx.android.synthetic.main.fragment_blog_detail.fabMenu
import kotlinx.android.synthetic.main.fragment_blog_detail.fabShare
import kotlinx.android.synthetic.main.fragment_blog_detail.ivType
import kotlinx.android.synthetic.main.fragment_blog_detail.toolbar
import kotlinx.android.synthetic.main.fragment_blog_detail.tvDesc

class BlogDetailFragment : BaseFragment<MainViewModel>() {

  companion object {
    const val ID = "ID"
  }

  private var blog: Blog? = null

  override fun layoutResId() = R.layout.fragment_blog_detail

  override fun attachedViewModel() = MainViewModel::class.java

  override fun initialize() {
    toolbar.setNavigationOnClickListener {
      findNavController().navigateUp()
    }
    fabMenu.bindAnchorView(fabShare) /*FloatingActionMenu : https://github.com/HarinTrivedi/FABRevealMenu-master*/
    fabMenu.setOnFABMenuSelectedListener { _, _ ->
      fabMenu.closeMenu()
    }
    fabLike.setOnClickListener {
      blog?.let { obj ->
        obj.fav = when {
          obj.fav == 0 -> 1
          else -> 0
        }
        viewMode.updateBlog(obj)
      }
    }


    observeData()
    arguments?.let {
      val id = it.getInt(ID)
      viewMode.getBlog(id)
    }
  }

  private fun observeData() {
    viewMode.blogData.observe(this, Observer {
      this.blog = it
      toolbar.title = it.title
      tvDesc.text = it.desc
      when (it.type) {
        Type.TRAVEL.value -> ivType.setImageResource(R.drawable.travel)
        Type.TECH.value -> ivType.setImageResource(R.drawable.tech)
        Type.BUSINESS.value -> ivType.setImageResource(R.drawable.business)
        Type.ART.value -> ivType.setImageResource(R.drawable.art)
      }
    })

    viewMode.blogFavData.observe(this, Observer {
      if (it)
        Snackbar.make(coordinatorLayout, "Marked as Favorite", Snackbar.LENGTH_SHORT).show()
    })
  }

}
