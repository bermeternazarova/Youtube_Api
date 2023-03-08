package com.example.youtube_api.ui.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtube_api.BuildConfig
import com.example.youtube_api.base.BaseViewModel
import com.example.youtube_api.model.Playlists
import com.example.youtube_api.remote.ApiService
import com.example.youtube_api.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistViewModel : BaseViewModel() {

    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    fun playlist(): LiveData<Playlists> {
        return getPlaylist()
    }

    private fun getPlaylist(): LiveData<Playlists> {
        val data = MutableLiveData<Playlists>()

        apiService.getPlaylists(
            BuildConfig.API_KEY,
            "UCWOA1ZGywLbqmigxE4Qlvuw",
            "snippet,contentDetails"
        )
            .enqueue(object : Callback<Playlists> {
                override fun onResponse(call: Call<Playlists>, response: Response<Playlists>) {
                    if (response.isSuccessful) {
                        data.value = response.body()
                    }
                }

                override fun onFailure(call: Call<Playlists>, t: Throwable) {
                    print(t.message)
                }
            })
        return data
    }
}