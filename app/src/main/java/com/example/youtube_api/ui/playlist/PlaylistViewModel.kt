package com.example.youtube_api.ui.playlist

import androidx.lifecycle.LiveData
import com.example.youtube_api.App.Companion.repository
import com.example.youtube_api.base.BaseViewModel
import com.example.youtube_api.data.remote.model.Playlists
import com.example.youtube_api.core.network.result.Resource

class PlaylistViewModel : BaseViewModel() {

    fun getPlaylist(): LiveData<Resource<Playlists>> {
        return repository.getPlaylist()
    }
}