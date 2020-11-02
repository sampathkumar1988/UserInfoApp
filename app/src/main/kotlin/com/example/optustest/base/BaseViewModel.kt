package com.example.optustest.base

import androidx.lifecycle.ViewModel
import com.example.optustest.injection.component.DaggerViewModelInjector
import com.example.optustest.injection.component.ViewModelInjector
import com.example.optustest.injection.module.NetworkModule
import com.example.optustest.ui.main.PhotosListViewModel
import com.example.optustest.ui.main.UserInfoListViewModel

/**
 * Base class for view model
 */
open class BaseViewModel: ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is UserInfoListViewModel -> injector.inject(this)
            is PhotosListViewModel -> injector.inject(this)
        }
    }
}