package com.rishabh.fillrtask.domain.use_case.get_image_details

import com.rishabh.fillrtask.common.Resource
import com.rishabh.fillrtask.data.remote.dto.PhotoDto
import com.rishabh.fillrtask.data.remote.dto.toPhoto
import com.rishabh.fillrtask.domain.model.Photo
import com.rishabh.fillrtask.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * A use case for fetching the details of a specific image from a list based on its index.
 * This use case encapsulates the logic needed to retrieve the details of an image, providing
 * a clear and concise API for the feature within the domain layer of the application.
 *
 * @constructor Creates an instance of GetImageDetailsUseCase. Marked with @Inject to support
 * dependency injection.
 */
class GetImageDetailsUseCase @Inject constructor() {

    /**
     * Retrieves the details of an image by its index in a provided list.
     * Emits a loading state before attempting the retrieval, followed by either
     * a success state with the image details or an error state.
     *
     * @param index The index of the image in the list whose details are to be fetched.
     * @param photoList The list of photos from which to fetch the image details.
     * @return A Flow emitting the state of the image retrieval operation.
     */
    operator fun invoke(index: Int?, photoList: List<Photo>?): Flow<Resource<Photo?>> = flow {
        try {
            emit(Resource.Loading<Photo?>())
            val photo = photoList?.let {
                if (index != null) {
                    getImageByIndex(index, it)
                } else {
                    throw NullPointerException()
                }
            }
            emit(Resource.Success<Photo?>(photo))
        } catch (exception: NullPointerException) {
            emit(
                Resource.Error(
                    exception.localizedMessage ?: ("An unexpected error " +
                            "occurred!")
                )
            )
        } catch (exception: IndexOutOfBoundsException) {
            emit(Resource.Error(exception.localizedMessage ?: "Index out of bounds!"))
        }
    }

    private fun getImageByIndex(index: Int, photoList: List<Photo>): Photo {
        return photoList[index]
    }
}