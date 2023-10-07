package com.tuf2000m.energymeter.data.remote.model.recent

data class Recents(
    val recentList: List<Recent>
){
    data class Recent(
        val guid: String,
        val timestamp: String
    )
}