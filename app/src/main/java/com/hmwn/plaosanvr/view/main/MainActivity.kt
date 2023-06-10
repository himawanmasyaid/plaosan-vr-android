package com.hmwn.plaosanvr.view.main

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.hmwn.plaosanvr.R
import com.hmwn.plaosanvr.common.DirectManager
import com.hmwn.plaosanvr.common.toast
import com.hmwn.plaosanvr.data.model.*
import com.hmwn.plaosanvr.databinding.ActivityMainBinding
import com.hmwn.plaosanvr.view.main.adapter.PanoramicAdapter
import com.hmwn.plaosanvr.view.main.adapter.VisitAdapter
import com.hmwn.plaosanvr.view.panoramic.PanoramicActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val visitAdapter by lazy {
        VisitAdapter(
            ::onVisitListener
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
        visitAdapter.resetData(getVisitPlaosan())

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
            rvVisit.apply {
                adapter = visitAdapter
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

    private fun onVisitListener(visit: VisitPlaosan) {

        when (visit.id) {
            VisitId.VIRTUAL_REALITY -> {
                toast("on progress")
            }
            VisitId.NAVIGATE_MAPS -> {
                gmapsDirectionToPlaosanTemple()
            }
            else -> {}
        }

    }

    fun gmapsDirectionToPlaosanTemple(latitude: Double = -7.7406277976550495, longitude: Double = 110.50471676490179) {
        val direct = DirectManager(this@MainActivity)
        direct.gmapsDirectionToLocation(latitude, longitude)
    }

}