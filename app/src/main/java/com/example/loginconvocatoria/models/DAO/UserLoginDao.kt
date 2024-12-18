package com.example.loginconvocatoria.models.DAO

import androidx.room.*
import com.example.loginconvocatoria.models.UserLoginEntity

@Dao
interface UserLoginDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserLogin(userLogin: UserLoginEntity)

    @Query("SELECT * FROM user_login LIMIT 1")
    suspend fun getUserLogin(): UserLoginEntity?

    @Query("DELETE FROM user_login")
    suspend fun clearUserLogin()
}
