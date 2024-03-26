package com.rishabh.fillrtask.data.remote.dto

data class ImageListDto(
    val photos: PhotosDto,
    val stat: String
)

fun ImageListDto.toPhotosDto(): PhotosDto {
    return photos
}