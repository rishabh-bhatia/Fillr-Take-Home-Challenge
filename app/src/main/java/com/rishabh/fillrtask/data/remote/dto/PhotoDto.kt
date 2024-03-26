package com.rishabh.fillrtask.data.remote.dto

import com.rishabh.fillrtask.domain.model.Photo

data class PhotoDto(
    val farm: Int,
    val id: String,
    val isfamily: Int,
    val isfriend: Int,
    val ispublic: Int,
    val owner: String,
    val secret: String,
    val server: String,
    val title: String
) {
    fun toImageUrl(): String = "https://live.staticflickr.com/${server}/${id}_${secret}.jpg"
}

fun PhotoDto.toPhoto(index: Int): Photo {
    return Photo(
        index,
        farm = farm,
        id = id,
        isfamily = isfamily,
        isfriend = isfriend,
        ispublic = ispublic,
        owner = owner,
        secret = secret,
        server = server,
        title = title,
        imageUrl = toImageUrl()
    )
}

