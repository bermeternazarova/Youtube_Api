package com.example.youtube_api

import android.app.Application
import com.example.youtube_api.repository.Repository

class App : Application() {
    companion object {
        val repository: Repository by lazy {
            Repository()
        }
    }
}