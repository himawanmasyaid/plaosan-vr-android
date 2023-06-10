package com.hmwn.plaosanvr.view.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import com.hmwn.plaosanvr.databinding.ActivityPanoramicBinding
import com.hmwn.plaosanvr.databinding.DialogInfoBinding

class DialogInfo(context: Context, title: String, message: String): Dialog(context) {

    private var binding : DialogInfoBinding

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding = DialogInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        if (title.isNotEmpty()) {
//            titleTv.text = title
//        } else {
//            titleTv.visibility = View.GONE
//        }
//
//        if (message.isNotEmpty()) {
//            desctiptionTv.text = message
//        } else {
//            desctiptionTv.visibility = View.GONE
//        }
//
//        actionBtn.onClick {
//            dismiss()
//        }

    }
}