package com.fawadjmalik.marsexplorer.feature.listscreen

import com.fawadjmalik.marsexplorer.core.utils.CoroutineTestRule
import com.fawadjmalik.marsexplorer.data.model.Camera
import com.fawadjmalik.marsexplorer.data.model.Photo
import com.fawadjmalik.marsexplorer.data.model.Rover
import com.fawadjmalik.marsexplorer.domain.usecase.MarsPhotosUseCase
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Author: Muhammad Fawad Jawaid Malik
 * This is the test class to test MarsPhotosListViewModel using Mockk, Junit, Kotest and Kotlinx.
 */
@ExperimentalCoroutinesApi
class MarsPhotosListViewModelTest {

    private lateinit var viewModel: MarsPhotosListViewModel

    private val marsPhotosUseCase = mockk<MarsPhotosUseCase>()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun setUp() {
        // empty body as view model should be initialized with mocking applied
    }

    @Test
    fun `initialize then fetch mars photos succeeded`() = runTest {
        // given
        val photo = Photo(
            camera = Camera(full_name = "abc", id = 12, name = "abc1", rover_id = 121),
            earth_date = "01.02.2002",
            id = 321,
            img_src = "dummy_url",
            rover = Rover(id = 123, landing_date = "2012-08-06", launch_date = "2011-11-26", name = "Curiosity", status = "Active"),
            sol = 100
        )

        every { marsPhotosUseCase.getMarsPhotos() } returns flow {
            emit(listOf(photo, photo))
        }

        // when
        // view model is initialized
        viewModel = MarsPhotosListViewModel(marsPhotosUseCase)

        // then
        viewModel.uiState.marsPhotos?.size shouldBe 2
        viewModel.uiState.isLoading shouldBe false
    }

    @Test
    fun `initialize then fetch mars photos failed`() = runTest {
        // given
        every { marsPhotosUseCase.getMarsPhotos() } returns flow {
            emit(emptyList())
        }

        // when
        // view model is initialized
        viewModel = MarsPhotosListViewModel(marsPhotosUseCase)

        // then
        viewModel.uiState.marsPhotos?.size shouldBe 0
        viewModel.uiState.isLoading shouldBe false
    }
}