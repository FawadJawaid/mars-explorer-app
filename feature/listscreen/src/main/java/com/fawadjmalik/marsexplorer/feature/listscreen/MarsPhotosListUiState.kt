package com.fawadjmalik.marsexplorer.feature.listscreen

import com.fawadjmalik.marsexplorer.data.model.Photo

/**
 * Author: Muhammad Fawad Jawaid Malik
 * This the MarsPhotosList UI state is what needs to be displayed on the screen.
 */
data class MarsPhotosListUiState (
    var marsPhotos: List<Photo>? = null,
    val isLoading: Boolean = true
)