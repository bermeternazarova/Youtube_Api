package com.example.youtube_api.ui.playlist.content

import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.youtube_api.R
import com.example.youtube_api.base.BaseActivity
import com.example.youtube_api.core.InternetConnection
import com.example.youtube_api.core.network.extensions.showToast
import com.example.youtube_api.core.network.result.Status
import com.example.youtube_api.data.remote.model.Items
import com.example.youtube_api.data.remote.model.PlaylistDetail
import com.example.youtube_api.databinding.ActivityContentBinding

class ContentActivity : BaseActivity<ContentViewModel,ActivityContentBinding>() {

    private val internetConnection: InternetConnection by lazy {
        InternetConnection(application = application) }
    private  val adapterContent: AdapterContent by lazy {
        AdapterContent()
    }
    private val id:String?
    get() = intent.getStringExtra(KEY)
    private lateinit var title:String

    override val viewModel: ContentViewModel by lazy {
        ViewModelProvider(this)[ContentViewModel::class.java]
    }

    override fun inflateViewBinding(inflater: LayoutInflater):ActivityContentBinding {
        return ActivityContentBinding.inflate(layoutInflater)
    }

    override fun checkInternet() {
        super.checkInternet()
        internetConnection.observe(this) { isConnected ->
            if (isConnected) {
                binding.includeInternetCheckContent.root.visibility = View.VISIBLE
            } else {
                binding.includeInternetCheckContent.root.visibility = View.GONE
            }
        }
    }
    override fun initViewModel() {
        super.initViewModel()
        viewModel.loading.observe(this){
            binding.progressBar.isVisible=it
        }
        viewModel.getItemOfPlaylist(id.toString()).observe(this){
            when(it.status){
                Status.SUCCESS->{
                    if (it.data!=null){
                        viewModel.loading.postValue(false)
                        binding.rvContent.adapter=adapterContent
                        adapterContent.addItem(it.data?.items as ArrayList<Items>)
                        setUpData(it.data)
                    }
                }
                Status.LOADING->{
                    viewModel.loading.postValue(true)
                    binding.toolbar.isVisible=false
                    binding.btnPlay.isVisible=false
                }
                Status.ERROR->{
                    viewModel.loading.postValue(true)
                    showToast("NO Internet Connection")
                }
            }
        }
    }

    private fun setUpData(data: PlaylistDetail) {
        if (intent!=null){
            title=intent.getStringExtra(KEY_OF_TITLE).toString()
        }
        binding.tvContentTitle.text=title
        binding.tvContentInfo.text=data.snippet?.description
        binding.tvSeries.text= data.items?.size.toString().plus(" ").plus(getString(R.string.video_series))
    }

    override fun initView() {
    }

    override fun initListener() {
        binding.tvBack.setOnClickListener {
            onBackPressed()
        }
    }
    companion object{
        const val KEY="ooo"
        const val KEY_OF_TITLE="aaa"
    }
}