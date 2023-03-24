package com.fawadjmalik.marsexplorer.domain.usecase

import com.fawadjmalik.marsexplorer.core.utils.CoroutineTestRule
import com.fawadjmalik.marsexplorer.data.model.Photo
import com.fawadjmalik.marsexplorer.data.repository.DataRepository
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Author: Muhammad Fawad Jawaid Malik
 * This is the unit test class to test MarsPhotosUseCase using Mockk, Junit, Kotest and Kotlinx.
 */
@ExperimentalCoroutinesApi
class MarsPhotosUseCaseTest {
    lateinit var marsPhotosUseCase: MarsPhotosUseCase

    private val dataRepository = mockk<DataRepository>()


    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun setup() {
        marsPhotosUseCase = MarsPhotosUseCase(dataRepository)
    }

    @Test
    fun `fetch mars photos`() = runTest {
        // given
        val mockMarsPhotos = mockk<List<Photo>>(relaxed = true)

        every {
            dataRepository.getMarsPhotos()
        } returns flow { emit(mockMarsPhotos) }

        // when
        val useCaseValue =
            marsPhotosUseCase.getMarsPhotos()

        //then
        useCaseValue.first() shouldBe mockMarsPhotos
    }
}