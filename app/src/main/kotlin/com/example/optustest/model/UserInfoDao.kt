package com.example.optustest.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserInfoDao {
    @get:Query("SELECT * FROM userinfo")
    val all: List<UserInfo>

    @Insert
    fun insertAll(vararg users: UserInfo)
}