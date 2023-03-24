package com.fawadjmalik.marsexplorer.feature.listscreen

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fawadjmalik.marsexplorer.data.model.Camera
import com.fawadjmalik.marsexplorer.data.model.Photo
import com.fawadjmalik.marsexplorer.data.model.Rover
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalComposeUiApi
@RunWith(AndroidJUnit4::class)
class ItemMarsPhotoCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun actionOnMarsPhotoItemTapped() {
        var isTapped = false
        val photo = Photo(
            camera = Camera(full_name = "abc", id = 12, name = "abc1", rover_id = 121),
            earth_date = "01.02.2002",
            id = 321,
            img_src = "dummy_url",
            rover = Rover(
                id = 123,
                landing_date = "2012-08-06",
                launch_date = "2011-11-26",
                name = "Curiosity",
                status = "Active"
            ),
            sol = 100
        )

        composeTestRule.setContent {
            ItemMarsPhotoCard(photo = photo, onItemClicked = { isTapped = true })
        }
        composeTestRule.onNode(hasClickAction()).performClick()
        assertTrue(isTapped)
    }
}
