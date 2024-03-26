package com.rishabh.fillrtask.di

import com.rishabh.fillrtask.common.Constants
import com.rishabh.fillrtask.data.remote.ImageServiceApi
import com.rishabh.fillrtask.data.repository.ImageRepositoryImpl
import com.rishabh.fillrtask.domain.repository.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * A Dagger Hilt module is installed in SingletonComponent, meaning the provided dependencies are
 * available application wide.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides a singleton instance of ImageServiceApi.
     * It constructs the Retrofit service for accessing the image API.
     *
     * @return An implementation of ImageServiceApi.
     */
    @Provides
    @Singleton
    fun provideImageServiceApi(): ImageServiceApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ImageServiceApi::class.java)

    }

    /**
     * Provides a singleton instance of ImageRepository.
     * It constructs the repository that will be used to interact with the network.
     *
     * @param api An instance of ImageServiceApi for the repository to use.
     * @return An implementation of ImageRepository.
     */
    @Provides
    @Singleton
    fun provideImageRepository(api: ImageServiceApi): ImageRepository {
        return ImageRepositoryImpl(api)
    }
}