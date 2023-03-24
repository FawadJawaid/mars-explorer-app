package com.fawadjmalik.data.remote.datasource

import com.fawadjmalik.marsexplorer.core.common.Resource
import com.fawadjmalik.marsexplorer.data.model.Photo

/**
 * Author: Muhammad Fawad Jawaid Malik
 */
interface RemoteSource {
    suspend fun getMarsPhotos(): Resource<List<Photo>>
}

