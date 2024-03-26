package com.rishabh.fillrtask.presentation.image_detail

import com.rishabh.fillrtask.domain.model.Photo

/**
 * Represents the UI state for the image detail screen in the application.
 * It holds the data required to render the screen, including the loading state, photo details,
 * and potential errors.
 *
 * @property isLoading Boolean indicating whether the image details are currently being loaded.
 * @property photo Optional [Photo] instance containing the details of the photo to be displayed.
 * Null if no photo is selected or available.
 * @property error String containing an error message if an error occurred while loading the photo
 * details. Empty if there's no error.
 */
data class ImageDetailState(
    val isLoading: Boolean = false,
    val photo: Photo? = null,
    val error: String = ""
)