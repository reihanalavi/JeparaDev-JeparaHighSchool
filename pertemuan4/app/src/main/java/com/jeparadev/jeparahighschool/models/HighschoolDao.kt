package com.jeparadev.jeparahighschool.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HighschoolDao {

    @Insert
    suspend fun insertHighschools(vararg highschool: Highschool): List<Long>

    @Query("SELECT * FROM highschool")
    suspend fun getHighschools(): List<Highschool>

    @Query("SELECT * FROM highschool WHERE uid = :uid")
    suspend fun getHighschoolByUid(uid: Int): Highschool

    @Query("SELECT * FROM highschool WHERE id = :id")
    suspend fun getHighschoolById(id: String): Highschool

    @Query("DELETE FROM highschool")
    suspend fun deleteHighschools()

}