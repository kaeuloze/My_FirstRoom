package com.example.myfirstroom.repositori

import kotlinx.coroutines.flow.Flow
import com.example.myfirstroom.room.Siswa
import com.example.myfirstroom.room.SiswaData

interface RepositoriSiswa {
    fun getAllSiswaStream(): Flow<List<Siswa>>

    suspend fun insertSiswa(siswa: Siswa)
}

class OfflineRepositoriSiswa(
    private val siswaData: SiswaData
) : com.example.myfirstroom.repositori.RepositoriSiswa {
    override fun getAllSiswaStream(): Flow<List<Siswa>> = siswaData.getAllSiswa()

    override suspend fun insertSiswa(siswa: Siswa) = siswaData.insert(siswa)
}