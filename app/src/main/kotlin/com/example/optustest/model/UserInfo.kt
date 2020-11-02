package com.example.optustest.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserInfo(@field:PrimaryKey var id: Int, var name: String, var username: String, var email:String, var phone: String)