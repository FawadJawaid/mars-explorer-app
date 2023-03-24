package com.fawadjmalik.marsexplorer.data.repository

import com.fawadjmalik.data.remote.datasource.RemoteSource
import com.fawadjmalik.marsexplorer.core.common.Resource
import com.fawadjmalik.marsexplorer.data.model.Photo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

/**
 * Author: Muhammad Fawad Jawaid Malik
 * This is the Repository class which fetches the data from the webservice.
 */
class DataRepository @Inject
constructor(
    private val remoteDataSource: RemoteSource
) : DataSource {

    override fun getMarsPhotos(): Flow<List<Photo>> {
        return flow {
            when (remoteDataSource.getMarsPhotos()) {
                is Resource.Success -> {
                    remoteDataSource.getMarsPhotos().data?.let { emit(it) }
                }
                is Resource.DataError -> {
                    emitAll(flowOf(emptyList()))
                }
                else -> {
                    emitAll(flowOf(emptyList()))
                }
            }
        }
    }
}
