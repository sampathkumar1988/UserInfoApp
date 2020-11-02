package com.example.optustest.injection.component

import com.example.optustest.injection.module.NetworkModule
import com.example.optustest.ui.main.PhotosListViewModel
import com.example.optustest.ui.main.UserInfoListViewModel
import dagger.Component
import javax.inject.Singleton


/**
 * An interface for providing inject methods for viewmodels.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    /**
     * Injects required dependencies into the specified UserInfoListViewModel.
     * @param userInfoListViewModel UserInfoListViewModel in which to inject the dependencies
     */
    fun inject(userInfoListViewModel : UserInfoListViewModel)

    /**
     * Injects required dependencies into the specified PhotosListViewModel.
     * @param photosListViewModel PhotosListViewModel in which to inject the dependencies
     */
    fun inject(photosListViewModel: PhotosListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}