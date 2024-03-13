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
            "Plaosan Lor",
            "plaosan_lor_bagian_depan.jpg",
            R.drawable.img_plaosan_lor
        ),
        Panoramic(
            "Plaosan Kidul",
            "plaosan_kidul.jpg",
            R.drawable.img_plaosan_kidul
        ),
        Panoramic(
            "Stupa Plaosan Lor",
            "stupa_plaosan_lor.jpg",
            R.drawable.img_stupa_plaosan
        ),
    )
}
