package com.example.loginconvocatoria.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.loginconvocatoria.models.DAO.UserLoginDao
import com.example.loginconvocatoria.models.UserLoginEntity


@Database(entities = [UserLoginEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userLoginDao(): UserLoginDao
}

