package com.example.optustest.ui.main

import androidx.lifecycle.MutableLiveData
import com.example.optustest.base.BaseViewModel
import com.example.optustest.model.UserInfo

class UserInfoViewModel : BaseViewModel() {
    private val id = MutableLiveData<String>()
    private val name = MutableLiveData<String>()
    private val phone = MutableLiveData<String>()
    private val email = MutableLiveData<String>()

    private val labelName = "Name :"
    private val labelPhone = "Phone :"
    private val labelEmail = "Email :"

    fun bind(userInfo: UserInfo) {
        id.value = userInfo.id.toString()
        name.value = labelName + userInfo.name
        phone.value = labelPhone + userInfo.phone
        email.value = labelEmail + userInfo.email
    }

    fun getId(): MutableLiveData<String> {
        return id
    }

    fun getName(): MutableLiveData<String> {
        return name
    }

    fun getPhone(): MutableLiveData<String> {
        return phone
    }

    fun getEmail(): MutableLiveData<String> {
        return email
    }
}