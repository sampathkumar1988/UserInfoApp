package com.example.optustest.ui.main

import androidx.lifecycle.MutableLiveData
import com.example.optustest.base.BaseViewModel
import com.example.optustest.model.Photo

class PhotosViewModel : BaseViewModel() {
    private val albumId = MutableLiveData<String>()
    private val id = MutableLiveData<String>()
    private val title = MutableLiveData<String>()
    private val url = MutableLiveData<String>()
    private val thumbnailUrl = MutableLiveData<String>()

    fun bind(photo: Photo) {
        albumId.value = photo.albumId.toString()
        id.value = photo.id.toString()
        title.value = photo.title
        url.value = photo.url
        thumbnailUrl.value = photo.thumbnailUrl
    }

    fun getAlbumId(): MutableLiveData<String> {
        return albumId
    }

    fun getId(): MutableLiveData<String> {
        return id
    }

    fun getTitle(): MutableLiveData<String> {
        return title
    }

    fun getURL(): MutableLiveData<String> {
        return url
    }

    fun getThumbnailUrl(): MutableLiveData<String> {
        return thumbnailUrl
    }
}