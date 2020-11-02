package com.example.optustest.injection

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.optustest.model.database.AppDatabase
import com.example.optustest.ui.main.PhotoViewModel
import com.example.optustest.ui.main.PhotosListViewModel
import com.example.optustest.ui.main.UserInfoListViewModel

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserInfoListViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "userinfo").build()
            @Suppress("UNCHECKED_CAST")
            return UserInfoListViewModel(db.userInfoDao()) as T
        }
        if (modelClass.isAssignableFrom(PhotosListViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "photo").build()
            @Suppress("UNCHECKED_CAST")
            return PhotosListViewModel(db.photoDao()) as T
        }
        if (modelClass.isAssignableFrom(PhotoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PhotoViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}