package com.hmwn.plaosanvr.view.vr

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.spherical.SphericalGLSurfaceView
import com.google.android.exoplayer2.upstream.AssetDataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.hmwn.plaosanvr.databinding.ActivityVirtualRealityBinding
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DataSource.Factory
import com.hmwn.plaosanvr.data.model.VirtualTour
import com.hmwn.plaosanvr.view.panoramic.PanoramicActivity

class VirtualRealityActivity : AppCompatActivity() {

    private lateinit var binding : ActivityVirtualRealityBinding
    private var videoPlayer: SimpleExoPlayer? = null

    private var playWhenReady: Boolean = true
    private var currentWindows = 0
    private var playBackPostion: Long = 0

    companion object {
        const val VR_URL = "vr_url_arg"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVirtualRealityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

    }

    private fun initView() {

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        val params = binding.playerView.layoutParams
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.MATCH_PARENT
        binding.playerView.layoutParams = params

        (binding.playerView.videoSurfaceView as SphericalGLSurfaceView)
            .setDefaultStereoMode(C.STEREO_MODE_TOP_BOTTOM)

    }

    private fun initializePlayer() {

        videoPlayer = SimpleExoPlayer.Builder(this).build()

        val vrUrl = intent.getStringExtra(VR_URL) ?: ""

        if (vrUrl.isEmpty()) {
            binding.tvNotAvailable.visibility
        } else {
            playStreamVideo(vrUrl)
        }

//        playAssetsVideo("congo-gorilla-vr.mp4")

    }

    private fun playStreamVideo(vrUrl: String) {

//        val url = "https://storage.googleapis.com/exoplayer-test-media-1/360/congo.mp4"

        val uri = Uri.parse(vrUrl)
        val mediaSource = buildMediaSource(uri)
        videoPlayer?.prepare(mediaSource)

        binding.playerView.player = videoPlayer
        binding.playerView.onResume()

    }

    private fun playAssetsVideo(fileName: String = "congo-gorilla-vr.mp4") {

        val dataSourceFactory: DataSource.Factory = object : Factory
        {
            override fun createDataSource(): DataSource
            {
                return AssetDataSource(this@VirtualRealityActivity)
            }
        }

        val videoSource = ExtractorMediaSource(Uri.parse("assets:///$fileName"), dataSourceFactory, DefaultExtractorsFactory(), null, null)
        videoPlayer?.prepare(videoSource)

        binding.playerView.player = videoPlayer
        binding.playerView.onResume()
    }

    override fun onStart() {
        super.onStart()
        if (isSupportedMultiWindow()) {
            initializePlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        hideSystemUi()
        if ((isSupportedMultiWindow() || videoPlayer == null)) {
            initializePlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        if (!isSupportedMultiWindow() || videoPlayer == null) {
            releasePlayer()
        }
    }

    private fun isSupportedMultiWindow() = Build.VERSION.SDK_INT > 23

    override fun onStop() {
        super.onStop()
        if (Build.VERSION.SDK_INT >= 24) {
            releasePlayer()
        }
    }

    private fun buildMediaSource(uri: Uri): MediaSource {
        val dataSourceFactory = DefaultDataSourceFactory(this, "javiermarsicano-VR-app")
        // Create a media source using the supplied URI
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(uri)
    }

    private fun releasePlayer() {
        if (videoPlayer != null) {
            playWhenReady = videoPlayer!!.playWhenReady
            playBackPostion = videoPlayer!!.currentPosition
            currentWindows = videoPlayer!!.currentWindowIndex
            videoPlayer!!.release()
            videoPlayer = null
        }
    }

    @SuppressLint("InlinedApi")
    private fun hideSystemUi() {
        binding.playerView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

    private fun setLog(msg: String) {
        Log.e("vr", msg)
    }

}