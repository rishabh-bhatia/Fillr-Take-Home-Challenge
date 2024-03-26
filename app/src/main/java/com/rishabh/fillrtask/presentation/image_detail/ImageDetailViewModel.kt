package com.rishabh.fillrtask.presentation.image_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rishabh.fillrtask.common.Resource
import com.rishabh.fillrtask.domain.model.Photo
import com.rishabh.fillrtask.domain.repository.ImagesDataRepository
import com.rishabh.fillrtask.domain.use_case.get_image_details.GetImageDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * ViewModel responsible for managing the UI state of the ImageDetailScreen and handling the
 * business logic to fetch image details.
 *
 * @param getImageDetailsUseCase Use case for fetching details of a specific image.
 * @param imagesDataRepository Repository for accessing images data including operations like
 * retrieving a list of images and updating the title.
 * @param savedStateHandle A handle to saved state passed into the ViewModel, used here to retrieve
 * the index of the image to be detailed.
 *
 * This ViewModel initializes by attempting to load the details of an image based on its index,
 * updating the UI state accordingly. It also supports updating the title in the images repository.
 */
@HiltViewModel
class ImageDetailViewModel @Inject constructor(
    private val getImageDetailsUseCase: GetImageDetailsUseCase,
    private val imagesDataRepository: ImagesDataRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(ImageDetailState())
    val state: State<ImageDetailState> = _state
    private val index: Int? = savedStateHandle["index"]

    init {
        index?.let { index ->
            val photoList = imagesDataRepository.getImagesList()
            getImageDetails(index, photoList)
        }
    }

    private fun getImageDetails(index: Int?, photoList: List<Photo>?) {
        getImageDetailsUseCase(index, photoList).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = ImageDetailState(photo = result.data)
                    state.value.photo?.title?.let { updateTitle(it) }
                }

                is Resource.Error -> {
                    _state.value = ImageDetailState(
                        error = result.message ?: "An unexpected error occured!"
                    )
                }

                is Resource.Loading -> {
                    _state.value = ImageDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun updateTitle(newTitle: String) {
        imagesDataRepository.setLastImageTitle(newTitle)
    }
}