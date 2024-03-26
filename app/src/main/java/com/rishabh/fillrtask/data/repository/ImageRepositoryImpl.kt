package com.rishabh.fillrtask.data.repository

import com.rishabh.fillrtask.data.remote.ImageServiceApi
import com.rishabh.fillrtask.data.remote.dto.toPhoto
import com.rishabh.fillrtask.data.remote.dto.toPhotoDtoList
import com.rishabh.fillrtask.data.remote.dto.toPhotosDto
import com.rishabh.fillrtask.domain.model.Photo
import com.rishabh.fillrtask.domain.repository.ImageRepository
import javax.inject.Inject

/**
 * An implementation of the ImageRepository interface that fetches images from a remote data source.
 * It uses ImageServiceApi to make network requests and converts the fetched data to a list of
 * domain model objects.
 *
 * @property api Instance of ImageServiceApi to fetch images from the network.
 */
class ImageRepositoryImpl @Inject constructor(
    private val api: ImageServiceApi
) : ImageRepository {

    /**
     * Fetches images from a remote data source, converts the data from ImageListDto to a list of
     * Photo domain models, and returns it. This conversion ensures that the app's domain logic is
     * decoupled from the API response structure.
     *
     * @return A list of Photo domain models.
     */
    override suspend fun getImages(): List<Photo> {
        // Fetch images using the API and convert the response to PhotosDto
        val photosDto = api.getImages().toPhotosDto()

        // Map each PhotoDto to the Photo domain model, maintaining an index for each photo
        return photosDto.toPhotoDtoList().mapIndexed { index, photoDto ->
            photoDto.toPhoto(index)
        }
    }
}
