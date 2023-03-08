package com.example.youtube_api.remote

import com.example.youtube_api.model.Playlists
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("playlists")
    fun getPlaylists(
        @Query("key") apiKey:String,
        @Query("channelId") channelId:String,
        @Query("part") part:String
    ): Call<Playlists>
}