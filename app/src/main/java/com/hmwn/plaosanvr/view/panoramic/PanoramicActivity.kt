package com.hmwn.plaosanvr.view.panoramic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hmwn.plaosanvr.databinding.ActivityPanoramicBinding

class PanoramicActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPanoramicBinding

    companion object {
        const val PHOTO_NAME_ARG = "photo_name_arg"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPanoramicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntentData()

    }

    private fun getIntentData() {

        val photo = intent.getStringExtra(PHOTO_NAME_ARG) ?: ""

        // set image to panorama
//        binding.panoramaView.setPanoramaBitmap(resource)

    }

    override fun onPause() {
        super.onPause()
        binding.panoramaView.onPause()
    }

    override fun onResume() {
        super.onResume()
        binding.panoramaView.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.panoramaView.destroyDrawingCache()
    }

}