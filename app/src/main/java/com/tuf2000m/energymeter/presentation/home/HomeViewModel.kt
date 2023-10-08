package com.tuf2000m.energymeter.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuf2000m.energymeter.data.model.meterdata.Data
import com.tuf2000m.energymeter.data.model.meterdata.MeterData
import com.tuf2000m.energymeter.data.model.meterdata.Timestamp
import com.tuf2000m.energymeter.data.model.recent.Recents
import com.tuf2000m.energymeter.data.remote.NetworkResult
import com.tuf2000m.energymeter.data.repository.MeterDataRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val meterDataRepo: MeterDataRepo,

    ) : ViewModel() {

    private var _meterData = MutableLiveData<NetworkResult<MeterData>>()
    val meterData: LiveData<NetworkResult<MeterData>> = _meterData

    private var _recentData = MutableLiveData<NetworkResult<Recents>>()
    val recentData: LiveData<NetworkResult<Recents>> = _recentData

    private var _searchData = MutableLiveData<List<Data>>()
    val searchData: LiveData<List<Data>> = _searchData

    fun getTimeStamps() {
        viewModelScope.launch {
            meterDataRepo.getTimeStamps().collect {
                _meterData.postValue(it)
            }
        }
    }

    fun getRecent() {
        viewModelScope.launch {
            meterDataRepo.getRecents().collect {
                _recentData.postValue(it)
            }
        }
    }

    fun searchData(timeStamps: List<Timestamp>, query: CharSequence?) {
        timeStamps.map { timeDto ->
            val dataList = timeDto.data.filter { dataDto ->
                dataDto.variableName.lowercase().contains(query.toString().lowercase())
            }
            _searchData.postValue(dataList)
        }


    }

}