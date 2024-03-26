package com.rishabh.fillrtask.data.remote

import com.rishabh.fillrtask.data.remote.dto.ImageListDto
import retrofit2.http.GET

/**
 * A Retrofit service interface for fetching details of photos from the "yuriy" API.
 * This interface defines network API calls for the application, using Retrofit annotations
 * to describe the HTTP request and parameters.
 */
interface ImageServiceApi {
    /**
     * Retrieves a list of recent photos.
     *
     * @return [ImageListDto] A data transfer object encapsulating the list of images.
     */
    @GET("/rakuten-rewards/photos.json?method=flickr.photos.getRecent&api_key=fee10de350d1f31d5fe" +
            "c0eaf330d2dba&page=1&format=json&nojsoncallback=true&safe_search=true")
    suspend fun getImages(): ImageListDto
}