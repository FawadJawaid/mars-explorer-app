package com.fawadjmalik.marsexplorer.feature.listscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.fawadjmalik.marsexplorer.core.common.base.BaseViewModel
import com.fawadjmalik.marsexplorer.domain.usecase.MarsPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Author: Muhammad Fawad Jawaid Malik
 * This is the MarsPhotosListViewModel, all the data has been fetched and modified here. It prepares all the data for the composable.
 * And business logic has been kept abstracted from MarsPhotosList composable.
 * This class has no knowledge whether data is coming from API or Database, so fulfilling complete abstraction of Data Layer.
 * We are using Flow to keep the data stream unidirectional.
 */
@HiltViewModel
class MarsPhotosListViewModel @Inject constructor(
    private val marsPhotosUseCase: MarsPhotosUseCase
) : BaseViewModel() {
    var uiState by mutableStateOf(MarsPhotosListUiState())
        private set

    init {
        getMarsPhotosList()
    }

    fun getMarsPhotosList() {
        // CoroutineScope tied to this ViewModel.
        // This scope will be canceled when ViewModel will be cleared, i.e ViewModel.onCleared is called
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            marsPhotosUseCase.getMarsPhotos().collect { result ->
                uiState = if (result.isNotEmpty()) {
                    uiState.copy(
                        isLoading = false,
                        marsPhotos = result
                    )
                } else {
                    uiState.copy(isLoading = false, marsPhotos = result)
                }
            }
        }
    }
}