package com.example.youtube_api.ui.playlist.content

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.example.youtube_api.base.BaseActivity
import com.example.youtube_api.databinding.ActivityContentBinding
import com.example.youtube_api.extensions.showToast
import com.example.youtube_api.ui.playlist.PlaylistViewModel

class ContentActivity : BaseActivity<PlaylistViewModel, ActivityContentBinding>() {

    override val viewModel: PlaylistViewModel by lazy {
        ViewModelProvider(this)[PlaylistViewModel::class.java]
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityContentBinding {
        return ActivityContentBinding.inflate(layoutInflater)
    }

    override fun initView() {
        showToast("${intent.getStringExtra("ooo")}")
    }

    override fun initListener() {
        binding.toolbarInclude.textViewBack.setOnClickListener {
            this.onBackPressed()
        }
    }
}