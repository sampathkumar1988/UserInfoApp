package com.example.optustest.network


import com.example.optustest.model.Photo
import com.example.optustest.model.UserInfo
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * The interface which provides methods to get the result of webservices
 */
interface AppService {

    /**
     * get the list of user info
     */
    @GET("/users")
    fun getUserInfo(): Observable<List<UserInfo>>

    /**
     * get the list of photos
     */
    @GET("/photos")
    fun getPhotos(): Observable<List<Photo>>
}