package com.example.loginconvocatoria.Database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.util.Log
import com.example.loginconvocatoria.models.UserLoginEntity


class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "app_database.db"
        const val DATABASE_VERSION = 1


        const val TABLE_USER = "user_login"
        const val COLUMN_ID = "id"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_TOKEN = "token"
        const val COLUMN_NAME = "nombreUsuario"
        const val COLUMN_ROLE = "rol"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
        CREATE TABLE $TABLE_USER (
            $COLUMN_ID INTEGER PRIMARY KEY,
            $COLUMN_EMAIL TEXT NOT NULL,
            $COLUMN_TOKEN TEXT NOT NULL,
            $COLUMN_NAME TEXT NOT NULL,
            $COLUMN_ROLE TEXT NOT NULL
        )
    """.trimIndent()
        db.execSQL(createTableQuery)

        // Log para confirmar la creación de la base de datos
        Log.d("Database", "Base de datos creada exitosamente")
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Manejar actualizaciones de la base de datos
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        onCreate(db)
    }

    // Insertar un usuario en la tabla
    fun insertUser(id: Int, email: String, token: String, nombreUsuario: String, rol: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_ID, id)
            put(COLUMN_EMAIL, email)
            put(COLUMN_TOKEN, token)
            put(COLUMN_NAME, nombreUsuario)
            put(COLUMN_ROLE, rol)
        }
        // Usa CONFLICT_REPLACE para evitar conflictos de claves duplicadas
        db.insertWithOnConflict(TABLE_USER, null, values, SQLiteDatabase.CONFLICT_REPLACE)
        db.close()
    }


    // Recuperar el usuario guardado
    fun getUser(): UserLoginEntity? {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_USER,
            null,
            null,
            null,
            null,
            null,
            null
        )
        return if (cursor.moveToFirst()) {
            val user = UserLoginEntity(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)),
                token = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TOKEN)),
                nombreUsuario = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                rol = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ROLE))
            )
            cursor.close()
            db.close()
            user
        } else {
            cursor.close()
            db.close()
            null
        }
    }

    // Eliminar todos los usuarios
    fun deleteAllUsers() {
        val db = writableDatabase
        db.delete(TABLE_USER, null, null)
        db.close()
    }
}

