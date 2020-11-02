package com.example.optustest.injection.module

import com.example.optustest.network.AppService
import com.example.optustest.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@Suppress("unused")
object NetworkModule {
    /**
     * Provides the User Info service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the User Info service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideUserInfoApi(retrofit: Retrofit): AppService {
        return retrofit.create(AppService::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }
}