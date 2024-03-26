package com.rishabh.fillrtask.di

import com.rishabh.fillrtask.domain.repository.ImagesDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *  This module is installed in SingletonComponent, ensuring that provided repositories are
 *  available application-wide and retain a singleton lifecycle.
 */

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    /**
     * Provides a singleton instance of ImagesDataRepository.
     * This method directly instantiates the ImagesDataRepository, ensuring there is a single
     * instance used throughout the app.
     *
     * @return A singleton instance of ImagesDataRepository.
     */
    @Singleton
    @Provides
    fun provideImagesDataRepository(): ImagesDataRepository = ImagesDataRepository()
}