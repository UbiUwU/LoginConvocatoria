package com.example.loginconvocatoria.Database

import android.content.Context
import androidx.room.Room

object DatabaseInstance {
    private var dbInstance: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        if (dbInstance == null) {
            dbInstance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            ).build()
        }
        return dbInstance!!
    }
}
