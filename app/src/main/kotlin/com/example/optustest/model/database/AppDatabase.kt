package com.example.optustest.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.optustest.model.Photo
import com.example.optustest.model.PhotoDao
import com.example.optustest.model.UserInfo
import com.example.optustest.model.UserInfoDao

@Database(entities = [UserInfo::class, Photo::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userInfoDao(): UserInfoDao
    abstract fun photoDao(): PhotoDao
}