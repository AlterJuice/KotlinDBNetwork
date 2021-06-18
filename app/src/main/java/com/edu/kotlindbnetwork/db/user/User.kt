package com.edu.kotlindbnetwork.db.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.edu.kotlindbnetwork.Consts

@Entity(tableName = Consts.tableNameUsers)
class User(
    @PrimaryKey var uid: String,
    @ColumnInfo(name = "first_name") var firstName: String?,
    @ColumnInfo(name = "last_name") var lastName: String?,
    @ColumnInfo(name = "phone_number") var phoneNumber: String?,
    @ColumnInfo(name = "email") var email: String?,
    @ColumnInfo(name = "photo_url") var photoUrl: String?

    // phone, urlPhoto, email
)