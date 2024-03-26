package com.rishabh.fillrtask.presentation.image_list

import com.rishabh.fillrtask.domain.model.Photo

data class ImageListState(
    val isLoading: Boolean = false,
    val photos: List<Photo> = emptyList(),
    val error: String = ""
)