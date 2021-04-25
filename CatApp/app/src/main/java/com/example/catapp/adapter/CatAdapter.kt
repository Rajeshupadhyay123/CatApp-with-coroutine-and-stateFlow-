package com.example.catapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.catapp.R
import com.example.catapp.databinding.ListItemBinding
import com.example.catapp.model.CatRequest

class CatAdapter : ListAdapter<CatRequest, CatAdapter.ViewHolder>(UserDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.fromViewHolder(parent)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        val imgUrl = data.url
        imgUrl.let {
            val imgUrlVar = imgUrl.toUri().buildUpon().scheme("https").build()
            holder.img.load(imgUrlVar) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
            }
        }
    }


    class ViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val img: ImageView = binding.catImage

        companion object {
            fun fromViewHolder(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }
}

class UserDiffUtil : DiffUtil.ItemCallback<CatRequest>() {
    override fun areItemsTheSame(oldItem: CatRequest, newItem: CatRequest): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: CatRequest, newItem: CatRequest): Boolean {
        return oldItem==newItem
    }

}