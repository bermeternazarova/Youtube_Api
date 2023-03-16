package com.example.youtube_api.ui.playlist.content

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube_api.databinding.ItemContentBinding
import com.example.youtube_api.core.network.extensions.glide
import com.example.youtube_api.data.remote.model.Items
import com.example.youtube_api.data.remote.model.Playlists

class AdapterContent() : RecyclerView.Adapter<AdapterContent.ViewHolderContent>() {

    private var list = ArrayList<Items>()

    fun addItem(item:ArrayList<Items>) {
        list.addAll(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderContent {
        return ViewHolderContent(
            ItemContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolderContent, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolderContent(private val binding: ItemContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemBind: Items) {
            binding.tvTime.text = itemBind.snippet.publishedAt.toString()
            binding.ivItemDetail.glide(itemBind.snippet.thumbnails.medium.url)
            binding.tvTitle.text = itemBind.snippet.title
        }

    }
}
