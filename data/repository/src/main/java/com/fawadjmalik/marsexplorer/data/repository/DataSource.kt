package com.fawadjmalik.marsexplorer.data.repository

import com.fawadjmalik.marsexplorer.data.model.Photo
import kotlinx.coroutines.flow.Flow


/**
 * Author: Muhammad Fawad Jawaid Malik
 * In order to have a clean architecture, I have created this interface and implementing it in DataRepository class.
*/
interface DataSource {
    fun getMarsPhotos(): Flow<List<Photo>>

}
