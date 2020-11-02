package com.example.optustest.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Photo(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "serialNo") var serialNo: Long?, var albumId: Int, var id: Int, var title:String?, var url: String?, var thumbnailUrl: String?)