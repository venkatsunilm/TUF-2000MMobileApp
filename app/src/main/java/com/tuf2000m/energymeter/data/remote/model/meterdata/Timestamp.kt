package com.tuf2000m.energymeter.data.remote.model.meterdata

data class Timestamp(
    val `data`: List<Data>,
    val guid: String,
    val timestamp: String
)