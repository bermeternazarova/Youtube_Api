package com.example.youtube_api.ui.playlist.content

import androidx.lifecycle.LiveData
import com.example.youtube_api.App.Companion.repository
import com.example.youtube_api.base.BaseViewModel
import com.example.youtube_api.core.network.result.Resource
import com.example.youtube_api.data.remote.model.Items
import com.example.youtube_api.data.remote.model.PlaylistDetail
import com.example.youtube_api.data.remote.model.Playlists

class ContentViewModel : BaseViewModel() {
    fun getItemOfPlaylist(playlistId:String): LiveData<Resource<PlaylistDetail>> {
        return repository.getItemOfPlaylist(playlistId=playlistId)
    }
}