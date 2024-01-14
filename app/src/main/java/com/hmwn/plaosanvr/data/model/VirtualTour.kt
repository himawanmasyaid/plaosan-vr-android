package com.hmwn.plaosanvr.data.model

import com.hmwn.plaosanvr.R

data class VirtualTour(
    val name: String,
    val icon: Int,
    val vr_url: String
)

private const val BASE_URL = "https://himawan.id/vr/"

fun getPlaosanVirtualTour(): List<VirtualTour> {
    return listOf(
        VirtualTour(
            name = "Tour VID 021",
            icon = R.drawable.img_plaosan,
            vr_url = BASE_URL + "VID_021.mp4"
        ),
        VirtualTour(
            name = "Tour VID 020",
            icon = R.drawable.img_compas,
            vr_url = BASE_URL + "VID_020.mp4"
        )
    )
}