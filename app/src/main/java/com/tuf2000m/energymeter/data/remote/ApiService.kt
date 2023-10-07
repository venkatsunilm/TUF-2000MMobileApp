package com.tuf2000m.energymeter.data.remote

import com.tuf2000m.energymeter.data.remote.model.meterdata.MeterData
import com.tuf2000m.energymeter.data.remote.model.recent.Recents
import retrofit2.http.GET

interface ApiService {
    @GET("meterdata")
    suspend fun timeStamps(): MeterData

    @GET("recentdata")
    suspend fun recents(): Recents
}