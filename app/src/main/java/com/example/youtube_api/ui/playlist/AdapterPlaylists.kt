package com.example.youtube_api.ui.playlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube_api.R
import com.example.youtube_api.databinding.ItemPlaylistsBinding
import com.example.youtube_api.extensions.glide
import com.example.youtube_api.model.Items
import com.example.youtube_api.model.Playlists

class AdapterPlaylists(
    private val playlists: Playlists,
    private val itemClick: (items: Items) -> Unit
) : RecyclerView.Adapter<AdapterPlaylists.ViewHolderPlaylists>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPlaylists {
        return ViewHolderPlaylists(
            ItemPlaylistsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolderPlaylists, position: Int) {
        holder.bind(playlists.items[position])
    }

    override fun getItemCount(): Int {
        return playlists.items.size
    }

    inner class ViewHolderPlaylists(private val binding: ItemPlaylistsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(items: Items) {
            binding.ivItemPlaylists.glide(items.snippet.thumbnails.medium.url)
            binding.tvItemPlaylists.text = items.snippet.title
            binding.tvDesk.text = items.contentDetails.itemCount.toString().plus(" ").plus(
                itemView.context.getString(
                    R.string.video_series
                )
            )
            itemView.setOnClickListener {
                itemClick(items)
            }
        }
    }
}