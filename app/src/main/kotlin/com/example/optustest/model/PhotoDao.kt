package com.example.optustest.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PhotoDao {
    @get:Query("SELECT * FROM photo")
    val all: List<Photo>

    @Insert
    fun insertAll(vararg photos: Photo)
}