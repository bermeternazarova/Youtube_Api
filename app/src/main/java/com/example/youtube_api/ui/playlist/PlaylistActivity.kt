package com.example.youtube_api.ui.playlist

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.youtube_api.core.InternetConnection
import com.example.youtube_api.R
import com.example.youtube_api.base.BaseActivity
import com.example.youtube_api.databinding.ActivityPlaylistBinding
import com.example.youtube_api.core.network.extensions.showToast
import com.example.youtube_api.core.network.result.Status
import com.example.youtube_api.data.remote.model.Items
import com.example.youtube_api.ui.playlist.content.ContentActivity

class PlaylistActivity : BaseActivity<PlaylistViewModel, ActivityPlaylistBinding>() {

    private lateinit var adapterPlaylists: AdapterPlaylists
    private val internetConnection: InternetConnection by lazy {
        InternetConnection(application = application) }
    private val registerForActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {}

    override val viewModel: PlaylistViewModel by lazy {
        ViewModelProvider(this)[PlaylistViewModel::class.java]
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlaylistBinding {
        return ActivityPlaylistBinding.inflate(layoutInflater)
    }

    override fun checkInternet() {
        internetConnection.observe(this) { isConnected ->
            if (isConnected) {
                binding.includeInternetCheck.root.visibility = View.GONE
            } else {
                binding.includeInternetCheck.root.visibility = View.VISIBLE
            }
        }
    }

    override fun initViewModel() {
        super.initViewModel()

        viewModel.loading.observe(this){
            binding.progressBar.isVisible=it
        }
        viewModel.getPlaylist().observe(this) {
            when(it.status){
                Status.SUCCESS->{
                    adapterPlaylists= AdapterPlaylists(this::itemClick,it)
                    binding.rvPlaylists.adapter = adapterPlaylists
                   // it.data?.let { it1 -> adapterPlaylists.setPllaylists(it1.items) }

                    viewModel.loading.postValue(false)
                }
                Status.LOADING->{
                    viewModel.loading.postValue(true)
                }
                Status.ERROR->{
                    viewModel.loading.postValue(true)
                    showToast(it.message.toString())
                }
            }
        }
    }

    private fun itemClick(items: Items) {
        val intent = Intent(this, ContentActivity::class.java).apply {
            putExtra(KEY, items.id)
            putExtra(KEY_OF_TITLE,items.snippet.title)
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
        const val KEY_OF_TITLE="aaa"
    }
}
