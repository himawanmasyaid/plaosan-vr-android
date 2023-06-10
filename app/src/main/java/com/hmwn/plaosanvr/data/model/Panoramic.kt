package com.hmwn.plaosanvr.data.model

import com.hmwn.plaosanvr.R

data class Panoramic(
    var name: String,
    var file_name: String,
    var picture: Int = R.drawable.rounded_grey
)

fun getPanoramicPhotos(): List<Panoramic> {
    return listOf(
        Panoramic(
            "Pintu Gerbang",
            "nat_callaghan.jpeg"
        ),
        Panoramic(
            "Candi Utama",
            "nat_callaghan"
        ),
        Panoramic(
            "Pintu Keluar",
            ""
        ),
    )
}
