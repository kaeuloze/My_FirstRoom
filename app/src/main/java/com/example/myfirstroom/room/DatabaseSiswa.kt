package com.example.myfirstroom.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Siswa::class],
    version = 1,
    exportSchema = false
)

abstract class DatabaseSiswa : RoomDatabase() {

    abstract fun siswaDao(): SiswaDao

    companion object {
        @Volatile
        private var Instance: com.example.myfirstroom.room.DatabaseSiswa? = null

        fun getDatabase(context: Context): com.example.myfirstroom.room.DatabaseSiswa {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    com.example.myfirstroom.room.DatabaseSiswa::class.java,
                    "siswa_database"
                ).build().also { Instance=it}
            }
        }
    }
}