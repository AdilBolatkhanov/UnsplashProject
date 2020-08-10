package com.example.unsplashproject.feature.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unsplashproject.R
import com.example.unsplashproject.feature.domain.entity.Image
import kotlinx.android.synthetic.main.image_item.view.*


class ImagesAdapter(
    private val clickListener: PhotoClickListener
):PagedListAdapter<Image, ImagesAdapter.ViewHolder>(diffCallback) {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {image->
            Glide.with(holder.itemView.context)
                .load(image.thumb_url)
                .into(holder.itemView.imageView)
            holder.itemView.setOnClickListener {
                clickListener.onClick(image)
            }
        }
    }

    companion object{
        private val diffCallback = object: DiffUtil.ItemCallback<Image>() {
            override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean = oldItem == newItem

            override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}

interface PhotoClickListener{
    fun onClick(photo: Image)
}