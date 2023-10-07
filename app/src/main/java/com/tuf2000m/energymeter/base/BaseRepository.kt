package com.tuf2000m.energymeter.base
import com.tuf2000m.energymeter.data.remote.NetworkResult
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

abstract class BaseRepository {
    suspend fun <T> safeapicall(apiCall: suspend () -> T)  = flow  {
        try {
            emit(NetworkResult.Loading(true))
            emit(NetworkResult.Success(apiCall.invoke()))
        } catch (e: HttpException) {
            emit(
                NetworkResult.Failure(e.localizedMessage ?: "Something went wrong please try again later."
                )
            )

        } catch (e: IOException) {
            emit(NetworkResult.Failure("No Internet please check your internet connection"))
        } catch (e: Exception) {
            emit(NetworkResult.Failure(e.localizedMessage ?: "Error"))
        } catch (e: Exception) {
            emit(NetworkResult.Failure(e.localizedMessage ?: "Error"))
        }
    }



}