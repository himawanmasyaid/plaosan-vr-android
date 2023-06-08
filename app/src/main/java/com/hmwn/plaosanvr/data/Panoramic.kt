package com.hmwn.plaosanvr.data

data class Panoramic(
    var name: String,
    var file_name: String
)

fun getPanoramicPhotos(): List<Panoramic> {
    return listOf(
        Panoramic(
            "Pintu Gerbang",
            ""
        ),
        Panoramic(
            "Candi Utama",
            ""
        ),
        Panoramic(
            "Pintu Keluar",
            ""
        ),
    )
}
