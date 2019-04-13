package com.hlabexamples.blogs.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hlabexamples.blogs.R
import com.hlabexamples.blogs.db.Blog
import com.hlabexamples.blogs.ui.BaseActivity
import com.hlabexamples.blogs.ui.BaseFragment
import com.hlabexamples.blogs.ui.overlaydemo.ThemeOverlayActivity
import com.hlabexamples.blogs.utils.OnItemClickListener
import com.hlabexamples.blogs.utils.setUpBottomAppBarShapeAppearance
import kotlinx.android.synthetic.main.content_home.btnToggle
import kotlinx.android.synthetic.main.content_home.rvBlog
import kotlinx.android.synthetic.main.content_home.toggleGroup
import kotlinx.android.synthetic.main.fragment_blog_list.bar
import kotlinx.android.synthetic.main.fragment_blog_list.fab

/**
 * A simple [Fragment] subclass.
 *
 */
class BlogListFragment : BaseFragment<MainViewModel>() {
  private lateinit var layoutManager: LayoutManager
  private var isGrid = false
  private var isFav = false

  override fun layoutResId() = R.layout.fragment_blog_list

  override fun attachedViewModel() = MainViewModel::class.java

  override fun initialize() {
    setUpBottomAppBarShapeAppearance(fab, bar)
    initListeners()
    observeData()
  }

  private fun initListeners() {
    setUpBottomAppBar()
    fab.setOnClickListener {
      findNavController().navigate(R.id.nav_new)
    }
    toggleGroup.addOnButtonCheckedListener { _, checkedId, selected ->
      if (selected) {
        when (checkedId) {
          R.id.btnTrending -> {
            isFav = false
            loadBlogs()
          }
          R.id.btnFavorite -> {
            isFav = true
            loadBlogs()
          }
        }
      }
    }
    btnToggle.setOnClickListener {
      isGrid = !isGrid
      assignLayoutManager()
    }
  }

  private fun setUpBottomAppBar() {
    bar.setNavigationOnClickListener {
      val bottomSheetDialog = BottomSheetDialog(activity!!)
      bottomSheetDialog.setContentView(R.layout.layout_info)
      bottomSheetDialog.findViewById<TextView>(R.id.btnClose)?.setOnClickListener { bottomSheetDialog.dismiss() }
      bottomSheetDialog.show()
    }
    bar.replaceMenu(R.menu.menu_toggle)
    bar.setOnMenuItemClickListener {
      when {
        it.itemId == R.id.th1 -> {
          changeTheme(R.style.AppTheme_Dark)
          return@setOnMenuItemClickListener true
        }
        it.itemId == R.id.th2 -> {
          changeTheme(R.style.AppTheme_Shape)
          return@setOnMenuItemClickListener true
        }
        it.itemId == R.id.th3 -> {
          changeTheme(R.style.AppTheme_Typography)
          return@setOnMenuItemClickListener true
        }
        it.itemId == R.id.th4 -> {
          changeTheme(R.style.AppTheme)
          return@setOnMenuItemClickListener true
        }
        it.itemId == R.id.th5 -> {
          startActivity(Intent(activity, ThemeOverlayActivity::class.java))
          return@setOnMenuItemClickListener true
        }
        else -> return@setOnMenuItemClickListener false
      }
    }
  }

  private fun changeTheme(appTheme: Int) {
    activity?.let {
      BaseActivity.theme = appTheme
      it.recreate()
    }
  }

  override fun onResume() {
    super.onResume()
    loadBlogs()
  }

  private fun initAdapter(list: List<Blog>) {
    rvBlog.adapter = null
    rvBlog.removeAllViews()
    assignLayoutManager()
    if (list.isNotEmpty()) {
      rvBlog.adapter = BlogListAdapter(list as ArrayList<Blog>, object : OnItemClickListener {
        override fun onItemClicked(position: Int) {
          val bundle = Bundle()
          val data = list[position]
          bundle.putInt(BlogDetailFragment.ID, data.id)

          findNavController().navigate(R.id.nav_detail, bundle)
        }
      })
    }
  }

  private fun assignLayoutManager() {
    when {
      isGrid -> layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
      else -> layoutManager = LinearLayoutManager(context)
    }
    rvBlog.layoutManager = layoutManager
  }

  private fun loadBlogs() {
    toggleGroup.isEnabled = false
    viewMode.loadData(isFav)
  }

  private fun observeData() {
    viewMode.blogLiveData.observe(this, Observer {
      initAdapter(it)
      toggleGroup.isEnabled = true
    })
  }

}
