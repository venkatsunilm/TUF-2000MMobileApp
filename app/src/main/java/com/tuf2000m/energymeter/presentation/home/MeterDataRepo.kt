package com.tuf2000m.energymeter.presentation.home


import com.tuf2000m.energymeter.base.BaseRepository
import com.tuf2000m.energymeter.data.remote.ApiService
import javax.inject.Inject

class MeterDataRepo @Inject constructor(private val apiService: ApiService) : BaseRepository() {

    suspend fun getTimeStamps() = safeapicall {
        apiService.timeStamps()
    }

    suspend fun getRecents() = safeapicall {
        apiService.recents()
    }
}

