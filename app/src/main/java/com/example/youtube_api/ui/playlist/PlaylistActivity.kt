package com.example.youtube_api.ui.playlist

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.youtube_api.InternetConnection
import com.example.youtube_api.R
import com.example.youtube_api.base.BaseActivity
import com.example.youtube_api.databinding.ActivityPlaylistBinding
import com.example.youtube_api.extensions.showToast
import com.example.youtube_api.model.Items
import com.example.youtube_api.ui.playlist.content.ContentActivity

class PlaylistActivity : BaseActivity<PlaylistViewModel, ActivityPlaylistBinding>() {

    private lateinit var adapterPlaylists: AdapterPlaylists
    private lateinit var internetConnection: InternetConnection
    private val registerForActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {}

    override val viewModel: PlaylistViewModel by lazy {
        ViewModelProvider(this)[PlaylistViewModel::class.java]
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlaylistBinding {
        return ActivityPlaylistBinding.inflate(layoutInflater)
    }

    override fun checkInternet() {
        internetConnection = InternetConnection(application = application)
        internetConnection.observe(this) { isConnected ->
            if (isConnected) {
                binding.includeInternetCheck.root.visibility = View.GONE
            } else {
                binding.includeInternetCheck.root.visibility = View.VISIBLE
            }
        }
    }

    override fun initView() {
        viewModel.playlist().observe(this) {
            adapterPlaylists = AdapterPlaylists(it, this::itemClick)
            binding.rvPlaylists.adapter = adapterPlaylists
        }
    }

    private fun itemClick(items: Items) {
        val intent = Intent(this, ContentActivity::class.java).apply {
            putExtra(KEY, items.id)
        }
        registerForActivity.launch(intent)
    }

    override fun initListener() {
        binding.includeInternetCheck.btnTryAgain.setOnClickListener {
            showToast(getString(R.string.no_connection))
        }
    }
    companion object{
        const val KEY="ooo"
    }
}
