package com.fawadjmalik.marsexplorer.domain.usecase

import com.fawadjmalik.marsexplorer.data.model.Photo
import kotlinx.coroutines.flow.Flow

/**
 * Author: Muhammad Fawad Jawaid Malik
 */
interface UseCase {
    fun getMarsPhotos(): Flow<List<Photo>>
}