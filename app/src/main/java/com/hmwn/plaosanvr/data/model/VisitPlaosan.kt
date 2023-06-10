package com.hmwn.plaosanvr.data.model

import com.hmwn.plaosanvr.R

data class VisitPlaosan(
    val id : Int,
    val name: String,
    val icon: Int
)

object VisitId {
    const val VIRTUAL_REALITY = 1
    const val NAVIGATE_MAPS = 2
}

fun getVisitPlaosan(): List<VisitPlaosan> {
    return listOf(
        VisitPlaosan(
            id = VisitId.VIRTUAL_REALITY,
            name = "Tour Virtual Reality",
            icon = R.drawable.img_plaosan
        ),
        VisitPlaosan(
            id = VisitId.NAVIGATE_MAPS,
            name = "Navigate to Plaosan Temple",
            icon = R.drawable.img_compas
        )
    )
}
