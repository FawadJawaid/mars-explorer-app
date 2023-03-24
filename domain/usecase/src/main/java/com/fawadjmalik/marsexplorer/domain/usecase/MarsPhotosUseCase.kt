package com.fawadjmalik.marsexplorer.domain.usecase

import com.fawadjmalik.marsexplorer.data.model.Photo
import com.fawadjmalik.marsexplorer.data.repository.DataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Author: Muhammad Fawad Jawaid Malik
 * As we are following the clean architecture, this is the use case class for mars photos. This use case
 * is later injected to the view model wherever it is required.
 */
class MarsPhotosUseCase @Inject constructor(
    private val dataRepository: DataSource
) : UseCase {
     override fun getMarsPhotos(): Flow<List<Photo>> {
        return dataRepository.getMarsPhotos()
    }
}