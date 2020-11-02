package com.example.optustest.ui.main

import androidx.lifecycle.MutableLiveData
import com.example.optustest.base.BaseViewModel


class PhotoViewModel : BaseViewModel() {

    private var title = MutableLiveData<String>()
    private var url = MutableLiveData<String>()

    fun bind(titleText: String?, imageUrl: String?) {
        title.value = titleText
        url.value = imageUrl
    }

    fun getTitle(): MutableLiveData<String> {
        return title
    }

    fun getURL(): MutableLiveData<String> {
        return url
    }
}