package com.fawadjmalik.marsexplorer.core.design.navigation

import androidx.annotation.StringRes

import com.fawadjmalik.marsexplorer.core.design.R
import com.fawadjmalik.marsexplorer.core.utils.Constants.DETAILS
import com.fawadjmalik.marsexplorer.core.utils.Constants.HOME

/**
 * Author: Muhammad Fawad Jawaid Malik
 * Sealed class for different navigation routes.
 */
sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Home : Screen(HOME, R.string.text_home)
    object Details : Screen(DETAILS, R.string.text_details)
}
