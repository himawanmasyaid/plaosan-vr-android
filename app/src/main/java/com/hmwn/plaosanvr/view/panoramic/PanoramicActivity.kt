package com.hmwn.plaosanvr.view.panoramic

import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.hmwn.plaosanvr.databinding.ActivityPanoramicBinding
import com.hmwn.plaosanvr.view.dialog.DialogInfo
import java.io.IOException

class PanoramicActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPanoramicBinding

    companion object {
        const val PHOTO_NAME_ARG = "photo_name_arg"
        const val FILE_NAME_ARG = "file_name_arg"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPanoramicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntentData()
        initListener()

    }

    private fun getIntentData() {

        val name = intent.getStringExtra(PHOTO_NAME_ARG) ?: ""
        val file_name = intent.getStringExtra(FILE_NAME_ARG) ?: ""

        val bitmap = getBitmapFromAsset(file_name)

        if (bitmap != null) {
            binding.panoramaView.setPanoramaBitmap(bitmap)
        } else {
            showInfoDialog(
                "Photo tidak ditemukan"
            )
        }

    }

    private fun initListener() {

        binding.btnClose.setOnClickListener {
            finish()
        }

    }

    fun getBitmapFromAsset(assetName: String): Bitmap? {
        var bitmap: Bitmap? = null
        try {
            val inputStream = assets.open(assetName)
            bitmap = BitmapFactory.decodeStream(inputStream)
        } catch (e: IOException) {
            return null
        }
        return bitmap
    }

    private fun showInfoDialog(title: String = "", desc: String = "") {
        val dialog = DialogInfo(
            this@PanoramicActivity,
            title,
            desc
        )
        dialog.show()
        dialog.setOnDismissListener {
            finish()
        }
    }

    override fun onPause() {
        super.onPause()
        try {
            binding.panoramaView.onPause()
        } catch (error: Exception) {

        }
    }

    override fun onResume() {
        super.onResume()
        try {
            binding.panoramaView.onResume()
        } catch (error: Exception) {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.panoramaView.destroyDrawingCache()
    }

    private fun setLog(msg: String) {
        Log.e("panorama", msg)
    }

}