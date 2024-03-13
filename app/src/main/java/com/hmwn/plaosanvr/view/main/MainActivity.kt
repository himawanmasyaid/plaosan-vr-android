package com.hmwn.plaosanvr.view.main

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.hmwn.plaosanvr.R
import com.hmwn.plaosanvr.common.DirectManager
import com.hmwn.plaosanvr.common.toast
import com.hmwn.plaosanvr.data.model.*
import com.hmwn.plaosanvr.databinding.ActivityMainBinding
import com.hmwn.plaosanvr.view.main.adapter.PanoramicAdapter
import com.hmwn.plaosanvr.view.main.adapter.VisitAdapter
import com.hmwn.plaosanvr.view.panoramic.PanoramicActivity
import com.hmwn.plaosanvr.view.virtualtour.VirtualTourActivity
import com.hmwn.plaosanvr.view.virtualtour.VirtualTourAdapter
import com.hmwn.plaosanvr.view.vr.VirtualRealityActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val virtualTourAdapter by lazy {
        VirtualTourAdapter(
            ::onVirtualTourListener
        )
    }

    private val panoramaAdapter by lazy {
        PanoramicAdapter(
            ::onPanoramicListener
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        panoramaAdapter.resetData(getPanoramicPhotos())
        virtualTourAdapter.resetData(getPlaosanVirtualTour())

    }

    private fun initView() {

        with(binding) {

            // setup panorama adapter
            rvPanoramicPhotos.apply {
                adapter = panoramaAdapter
                apply {
                    isNestedScrollingEnabled = false
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                }
            }

            // setup visit adapter
            rvVirtualTour.apply {
                adapter = virtualTourAdapter
                apply {
                    isNestedScrollingEnabled = false
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                }
            }

        }

    }

    private fun onPanoramicListener(panoramic: Panoramic) {
        val intent = Intent(this, PanoramicActivity::class.java)
        intent.putExtra(PanoramicActivity.PHOTO_NAME_ARG, panoramic.name)
        intent.putExtra(PanoramicActivity.FILE_NAME_ARG, panoramic.file_name)
        startActivity(intent)
    }

    private fun onVirtualTourListener(vr: VirtualTour) {
        val intent = Intent(this, VirtualRealityActivity::class.java)
        intent.putExtra(VirtualRealityActivity.VR_URL, vr.vr_url)
        startActivity(intent)
    }

    private fun onVisitListener(visit: VisitPlaosan) {

        when (visit.id) {
            VisitId.VIRTUAL_REALITY -> {
                startActivity(Intent(this, VirtualTourActivity::class.java))
            }
            VisitId.NAVIGATE_MAPS -> {
                gmapsDirectionToPlaosanTemple()
            }
            else -> {}
        }

    }

    fun gmapsDirectionToPlaosanTemple(
        latitude: Double = -7.74083,
        longitude: Double = 110.50461
    ) {
        DirectManager(this@MainActivity).gmapsOpenDetailLocation(latitude, longitude, "Candi Plaosan")
    }

}