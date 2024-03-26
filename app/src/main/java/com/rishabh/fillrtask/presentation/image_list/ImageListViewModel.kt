package com.rishabh.fillrtask.presentation.image_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rishabh.fillrtask.common.Resource
import com.rishabh.fillrtask.domain.repository.ImagesDataRepository
import com.rishabh.fillrtask.domain.use_case.get_images.GetImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.jetbrains.annotations.VisibleForTesting
import javax.inject.Inject

/**
 * ViewModel responsible for managing the state and data for the image list screen in the
 * application.
 *
 * This ViewModel interacts with the domain layer to fetch a list of images using the
 * GetImagesUseCase. It maintains the state of the image list screen, including the list of photos,
 * loading status, and any errors that may occur during data fetching. The ViewModel uses a Kotlin
 * Flow to observe changes in the data layer and updates its state accordingly. It also communicates
 * with the ImagesDataRepository to store and retrieve the last viewed image title, which is
 * observed and displayed on the UI.
 *
 * @property getImagesUseCase The use case for fetching images from the repository.
 * @property imagesDataRepository Repository for storing and retrieving image and title data.
 */
@HiltViewModel
class ImageListViewModel @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase,
    private val imagesDataRepository: ImagesDataRepository
) : ViewModel() {

    private val _state = mutableStateOf(ImageListState())
    val state: State<ImageListState> = _state
    val title = imagesDataRepository.lastTitle

    init {
        getImages()
    }

    @VisibleForTesting
    fun getImages() {
        getImagesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = ImageListState(photos = result.data ?: emptyList())
                    imagesDataRepository.storeImagesList(_state.value.photos)
                }

                is Resource.Error -> {
                    _state.value = ImageListState(
                        error = result.message ?: "An unexpected error occured!"
                    )
                }

                is Resource.Loading -> {
                    _state.value = ImageListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}