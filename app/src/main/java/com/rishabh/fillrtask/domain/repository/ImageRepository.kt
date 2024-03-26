package com.rishabh.fillrtask.domain.repository

import com.rishabh.fillrtask.domain.model.Photo

interface ImageRepository {

    suspend fun getImages(): List<Photo>

}