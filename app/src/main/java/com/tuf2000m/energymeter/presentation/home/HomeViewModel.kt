package com.tuf2000m.energymeter.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuf2000m.energymeter.data.remote.NetworkResult
import com.tuf2000m.energymeter.data.remote.model.meterdata.Data
import com.tuf2000m.energymeter.data.remote.model.meterdata.MeterData
import com.tuf2000m.energymeter.data.remote.model.meterdata.Timestamp
import com.tuf2000m.energymeter.data.remote.model.recent.Recents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val meterDataRepo: MeterDataRepo,

    ) : ViewModel() {

    private var _meterdata = MutableLiveData<NetworkResult<MeterData>>()
    val meterdata: LiveData<NetworkResult<MeterData>> = _meterdata

    private var _recentdata = MutableLiveData<NetworkResult<Recents>>()
    val recentdata: LiveData<NetworkResult<Recents>> = _recentdata

    private var _searchdata = MutableLiveData<List<Data>>()
    val searchdata: LiveData<List<Data>> = _searchdata

    fun getTimeStamps() {
        viewModelScope.launch {
            meterDataRepo.getTimeStamps().collect {
                _meterdata.postValue(it)
            }
        }
    }

    fun getRecents() {
        viewModelScope.launch {
            meterDataRepo.getRecents().collect {
                _recentdata.postValue(it)
            }
        }
    }

    fun searchData(timeStamps: List<Timestamp>, query: CharSequence?) {
        timeStamps.map { timeDto ->
            val dataList = timeDto.data.filter { dataDto ->
                dataDto.variableName.lowercase().contains(query.toString().lowercase())
            }
            _searchdata.postValue(dataList)
        }

    }

}