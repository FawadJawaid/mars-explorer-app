package com.fawadjmalik.marsexplorer.feature.detailscreen

import com.fawadjmalik.marsexplorer.core.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Author: Muhammad Fawad Jawaid Malik
 * This is the MarsPhotoDetailsViewModel, all the data can be fetched and modified here. It prepares all the data for the composable.
 * And business logic has been kept abstracted from MarsPhotoDetailsViewModel composable.
 * This class has no knowledge whether data is coming from API or Database, so fulfilling complete abstraction of Data Layer.
 *
 * It is empty because we are just passing data from list composable to details composable, there is no new API consumption.
 */
@HiltViewModel
class MarsPhotoDetailsViewModel @Inject constructor() : BaseViewModel()