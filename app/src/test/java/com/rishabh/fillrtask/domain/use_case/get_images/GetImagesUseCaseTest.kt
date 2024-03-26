package com.rishabh.fillrtask.domain.use_case.get_images

import com.rishabh.fillrtask.common.Resource
import com.rishabh.fillrtask.domain.model.Photo
import com.rishabh.fillrtask.domain.repository.ImageRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetImagesUseCaseTest {

    @Mock
    private lateinit var imageRepository: ImageRepository
    private lateinit var getImagesUseCase: GetImagesUseCase

    @Before
    fun setUp() {
        getImagesUseCase = GetImagesUseCase(imageRepository)
    }

    @Test
    fun `when repository returns success, then emit success resource`() = runTest {
        val expectedPhotos = listOf(Photo(0,0,"1",1,1,1,
            "1", "1","1", "1", "1"), Photo(7,2,
            "27",1,1,1, "1", "1","1", "007",
            "1"))
        whenever(imageRepository.getImages()).thenReturn(expectedPhotos)

        val result = getImagesUseCase().toList()

        assert(result.last() is Resource.Success)
        assert((result.last() as Resource.Success<List<Photo>>).data == expectedPhotos)
    }
}





