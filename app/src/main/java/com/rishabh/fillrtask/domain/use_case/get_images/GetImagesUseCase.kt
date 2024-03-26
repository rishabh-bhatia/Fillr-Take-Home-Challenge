package com.rishabh.fillrtask.domain.use_case.get_images

import com.rishabh.fillrtask.common.Resource
import com.rishabh.fillrtask.data.remote.dto.ImageListDto
import com.rishabh.fillrtask.data.remote.dto.toPhoto
import com.rishabh.fillrtask.data.remote.dto.toPhotoDtoList
import com.rishabh.fillrtask.data.remote.dto.toPhotosDto
import com.rishabh.fillrtask.domain.model.Photo
import com.rishabh.fillrtask.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * A use case for fetching a list of images from a repository. This class contains the logic
 * for retrieving images, making it easier to interact with the data layer.
 *
 * @constructor Injects the ImageRepository to access the data layer.
 * @param repository The repository from which images are fetched.
 */
class GetImagesUseCase @Inject constructor(
    private val repository: ImageRepository
) {
    /**
     * Invokes the use case to fetch a list of images.
     * This function emits a loading state before making the network request, followed by
     * a success state with the list of images or an error state with an appropriate message.
     *
     * @return A flow emitting the loading, success, or error state of the image retrieval operation.
     */
    operator fun invoke(): Flow<Resource<List<Photo>>> = flow {
        try {
            emit(Resource.Loading<List<Photo>>())
            val images = repository.getImages()
            emit(Resource.Success<List<Photo>>(images))
        } catch (exception: HttpException) {
            emit(
                Resource.Error<List<Photo>>(
                    exception.localizedMessage ?: "An unexpected error occured"
                )
            )
        } catch (exception: IOException) {
            emit(
                Resource.Error<List<Photo>>(
                    "Couldn't reach server. Check your internet " +
                            "connection."
                )
            )
        }

    }
}