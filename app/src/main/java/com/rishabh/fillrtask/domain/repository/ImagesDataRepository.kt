package com.rishabh.fillrtask.domain.repository

import com.rishabh.fillrtask.domain.model.Photo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This repository utilizes StateFlow to expose the last image title and allows storing and
 * retrieving of a list of photos mainly for communication between different screens/ViewModels.
 *
 * This repository is designed as a Singleton to ensure a single instance is used throughout the
 * app's lifecycle.
 *
 * @constructor Creates an ImagesDataRepository instance. Marked with @Inject to support dependency
 * injection via Dagger Hilt.
 */
@Singleton
class ImagesDataRepository @Inject constructor() {
    private var currentList: List<Photo>? = null
    private val _lastTitle = MutableStateFlow("Title")
    val lastTitle: StateFlow<String> = _lastTitle.asStateFlow()

    /**
     * Stores a list of photos in the repository.
     * @param list List of photos to be stored.
     */
    fun storeImagesList(list: List<Photo>) {
        currentList = list
    }

    /**
     * Retrieves the stored list of photos.
     * @return List of photos or null if no list has been stored.
     */
    fun getImagesList(): List<Photo>? = currentList

    /**
     * Updates the title of the last image in the repository.
     * @param title The new title for the last image.
     */
    fun setLastImageTitle(title: String) {
        _lastTitle.value = title
    }
}