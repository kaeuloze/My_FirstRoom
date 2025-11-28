package com.example.myfirstroom.repositori

import kotlinx.coroutines.flow.Flow
import com.example.myfirstroom.room.Siswa
import com.example.myfirstroom.room.SiswaDao

interface RepositoriSiswa {
    fun getAllSiswaStream(): Flow<List<Siswa>>

    suspend fun insertSiswa(siswa: Siswa)
}

class OfflineRepositoriSiswa(
    private val siswaDao: SiswaDao
) : com.example.myfirstroom.repositori.RepositoriSiswa {
    override fun getAllSiswaStream(): Flow<List<Siswa>> = siswaDao.getAllSiswa()

    override suspend fun insertSiswa(siswa: Siswa) = siswaDao.insert(siswa)
}