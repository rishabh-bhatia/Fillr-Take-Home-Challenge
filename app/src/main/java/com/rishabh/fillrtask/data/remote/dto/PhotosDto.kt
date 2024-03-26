package com.rishabh.fillrtask.data.remote.dto

data class PhotosDto(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val photo: List<PhotoDto>,
    val total: Int
)

fun PhotosDto.toPhotoDtoList(): List<PhotoDto> {
    return photo
}