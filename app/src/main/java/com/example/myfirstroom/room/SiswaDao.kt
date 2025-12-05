package com.example.myfirstroom.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SiswaDao {
    @Query(value = "SELECT * from tblSiswa ORDER BY nama ASC")
    fun getAllSiswa(): Flow<List<Siswa>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(siswa: Siswa)

    //edit 3 fungsi
    @Query("SELECT * from tblSiswa WHERE id = :id")
    fun getSiswa(id: Int ): Flow<Siswa>

    @Delete
    suspend fun delete(siswa: Siswa)

    @Update
    suspend fun Update(siswa: Siswa)
}