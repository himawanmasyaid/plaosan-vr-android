package com.hmwn.plaosanvr.view.virtualtour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.hmwn.plaosanvr.R
import com.hmwn.plaosanvr.data.model.VirtualTour
import com.hmwn.plaosanvr.data.model.getPlaosanVirtualTour
import com.hmwn.plaosanvr.databinding.ActivityVirtualRealityBinding
import com.hmwn.plaosanvr.databinding.ActivityVirtualTourBinding
import com.hmwn.plaosanvr.view.main.adapter.VisitAdapter
import com.hmwn.plaosanvr.view.vr.VirtualRealityActivity

class VirtualTourActivity : AppCompatActivity() {

    private lateinit var binding : ActivityVirtualTourBinding

    private val virtualTourAdapter by lazy {
        VirtualTourAdapter(
            ::onVirtualTourListener
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVirtualTourBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

        virtualTourAdapter.resetData(getPlaosanVirtualTour())

    }

    private fun initView() {

        with(binding) {
            // setup panorama adapter
            rvVirtualTour.apply {
                adapter = virtualTourAdapter
                apply {
                    isNestedScrollingEnabled = false
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                }
            }

            ivBack.setOnClickListener {
                finish()
            }

        }


    }

    private fun onVirtualTourListener(vr: VirtualTour) {
        val intent = Intent(this, VirtualRealityActivity::class.java)
        intent.putExtra(VirtualRealityActivity.VR_URL, vr.vr_url)
        startActivity(intent)
    }


}