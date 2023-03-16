package com.example.youtube_api.data.remote

import com.example.youtube_api.data.remote.model.ItemPLaylist
import com.example.youtube_api.data.remote.model.Playlists
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("playlists")
    fun getPlaylists(
        @Query("key") apiKey: String,
        @Query("channelId") channelId: String,
        @Query("part") part: String,
        @Query("maxResults") maxResults: Int
    ): Call<Playlists>

    @GET("playlistItems")
    fun getItemOfPlaylist(
        @Query("key") apiKey: String,
        @Query("playlistId") playlistId:String,
        @Query("part") part: String,
        @Query("maxResults") maxResults: Int
    ): Call<ItemPLaylist>
}