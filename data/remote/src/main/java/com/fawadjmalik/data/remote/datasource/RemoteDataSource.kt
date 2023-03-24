package com.fawadjmalik.data.remote.datasource

import android.util.Log
import com.fawadjmalik.data.remote.network.MarsPhotosApi
import com.fawadjmalik.marsexplorer.core.common.Resource
import com.fawadjmalik.marsexplorer.data.model.Photo
import javax.inject.Inject

/**
 * Author: Muhammad Fawad Jawaid Malik
 * This is the remote data source, we are fetching data from remote source in our case, mars photos api.
 */

class RemoteDataSource @Inject
constructor(private val api: MarsPhotosApi) : RemoteSource {

    override suspend fun getMarsPhotos(): Resource<List<Photo>> {
        try {
            val res = api.fetchPhotos()

            when (res.isSuccessful) {
                true -> {
                    res.body()?.let { body ->
                        if (body.photos.isNotEmpty()) {
                            return Resource.Success(data = body.photos)
                        } else return Resource.DataError(errorCode = 101)
                    } ?: return Resource.DataError(errorCode = res.code())
                }
                false -> {
                    return Resource.DataError(errorCode = res.code())
                }
            }
        } catch (e: Exception) {
            Log.e("NETWORK_API_ERROR", "Cannot get mars images ${e.message}")
            return Resource.DataError(errorCode = e.hashCode())
        }
    }
}
