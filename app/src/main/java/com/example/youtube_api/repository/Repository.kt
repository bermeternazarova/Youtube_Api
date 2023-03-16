package com.example.youtube_api.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtube_api.BuildConfig
import com.example.youtube_api.core.network.RetrofitClient
import com.example.youtube_api.core.network.result.Resource
import com.example.youtube_api.data.remote.ApiService
import com.example.youtube_api.data.remote.model.ItemPLaylist
import com.example.youtube_api.data.remote.model.Playlists
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }
     fun getPlaylist(): LiveData<Resource<Playlists>> {
        val data = MutableLiveData<Resource<Playlists>>()

         data.value=Resource.loading()

        apiService.getPlaylists(
            BuildConfig.API_KEY,
            "UCWOA1ZGywLbqmigxE4Qlvuw",
            "snippet,contentDetails",
            30
        )
            .enqueue(object : Callback<Playlists> {
                override fun onResponse(call: Call<Playlists>, response: Response<Playlists>) {
                   // if (response.isSuccessful) {
                        data.value =Resource.success(response.body())
                   // }
                }
                override fun onFailure(call: Call<Playlists>, t: Throwable) {
                    data.value =Resource.error(t.message,null,null)
                }
            })
        return data
    }
    fun getItemOfPlaylist(playlistId:String): LiveData<Resource<ItemPLaylist>> {
        val item = MutableLiveData<Resource<ItemPLaylist>>()

        apiService.getItemOfPlaylist(
            BuildConfig.API_KEY,
            playlistId = playlistId,
            "snippet,contentDetails",
            40
        ).enqueue(object : Callback<ItemPLaylist> {
            override fun onResponse(call: Call<ItemPLaylist>, response: Response<ItemPLaylist>) {
                item.value =Resource.success(response.body())
            }

            override fun onFailure(call: Call<ItemPLaylist>, t: Throwable) {
            item.value=Resource.error(t.message,null,null)
            }
        })
        return item
    }
}