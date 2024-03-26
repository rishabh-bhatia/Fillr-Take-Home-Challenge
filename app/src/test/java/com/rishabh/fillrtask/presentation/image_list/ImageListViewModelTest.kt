package com.rishabh.fillrtask.presentation.image_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rishabh.fillrtask.common.Resource
import com.rishabh.fillrtask.domain.model.Photo
import com.rishabh.fillrtask.domain.repository.ImagesDataRepository
import com.rishabh.fillrtask.domain.use_case.get_images.GetImagesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class ImageListViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    @Mock
    private lateinit var getImagesUseCase: GetImagesUseCase

    @Mock
    private lateinit var imagesDataRepository: ImagesDataRepository

    private lateinit var viewModel: ImageListViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)

        viewModel = ImageListViewModel(getImagesUseCase, imagesDataRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun whenGetImagesUseCaseReturnsSuccessUpdateStateWithPhotos() = runTest {
        val fakePhotos = listOf(Photo(1, 1, "test", 0, 1, 0,
            "dff", "test", "test", "titleTest1",
            "http://test.com"), Photo(2, 15, "test", 0, 1,
            0, "dff", "test", "test", "titleTest2",
            "https://test.com"))

        val flow = flowOf(Resource.Success(fakePhotos))
        `when`(getImagesUseCase()).thenReturn(flow)

        viewModel.getImages()

        assert(viewModel.state.value.photos == fakePhotos)
    }
}
