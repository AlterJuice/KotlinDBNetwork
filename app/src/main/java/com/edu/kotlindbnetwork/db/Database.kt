package com.edu.kotlindbnetwork.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.edu.kotlindbnetwork.db.user.User
import com.edu.kotlindbnetwork.db.user.UserDao


@Database(entities = [User::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun userDao(): UserDao?
}