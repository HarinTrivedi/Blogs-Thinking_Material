package com.hlabexamples.blogs.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hlabexamples.blogs.R
import com.hlabexamples.blogs.db.Blog
import com.hlabexamples.blogs.ui.main.BlogListAdapter.BlogViewHolder
import com.hlabexamples.blogs.utils.OnItemClickListener
import com.hlabexamples.blogs.utils.Type.ART
import com.hlabexamples.blogs.utils.Type.BUSINESS
import com.hlabexamples.blogs.utils.Type.TECH
import kotlinx.android.synthetic.main.row_blogs.view.ivType
import kotlinx.android.synthetic.main.row_blogs.view.tvDesc
import kotlinx.android.synthetic.main.row_blogs.view.tvTitle

class BlogListAdapter(
  private var list: ArrayList<Blog>,
  private var onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<BlogViewHolder>() {

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): BlogViewHolder {
    return BlogViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_blogs, parent, false), onItemClickListener)
  }

  override fun getItemCount() = list.size

  override fun onBindViewHolder(
    holder: BlogViewHolder,
    position: Int
  ) {
    holder.bind(list[position])
  }

  class BlogViewHolder(
    itemView: View,
    private val onItemClickListener: OnItemClickListener
  ) : RecyclerView.ViewHolder(itemView) {
    fun bind(data: Blog) {
      itemView.tvTitle.text = data.title
      itemView.tvDesc.text = data.desc
      itemView.ivType.setImageResource(
        getImageFromType(data)
      )

      itemView.setOnClickListener {
        onItemClickListener.onItemClicked(adapterPosition)
      }
    }

    private fun getImageFromType(data: Blog): Int {
      return when (data.type) {
        BUSINESS.value -> R.drawable.ic_business
        TECH.value -> R.drawable.ic_tech
        ART.value -> R.drawable.ic_art
        else -> R.drawable.ic_travel
      }
    }

  }

}