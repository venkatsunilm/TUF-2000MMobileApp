package com.tuf2000m.energymeter.data.model.meterdata

data class Timestamp(
    val `data`: List<Data>,
    val guid: String,
    val timestamp: String
)