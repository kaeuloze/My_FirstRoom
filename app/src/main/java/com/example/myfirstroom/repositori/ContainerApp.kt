package com.example.myfirstroom.repositori

import android.app.Application
import android.content.Context
import com.example.myfirstroom.room.DatabaseSiswa

interface ContainerApp {
    val repositoriSiswa: RepositoriSiswa
}

class ContainerDataApp(private val context: Context):
    com.example.myfirstroom.repositori.ContainerApp {
    override val repositoriSiswa: RepositoriSiswa by lazy {
        OfflineRepositoriSiswa(
            siswaData = DatabaseSiswa.getDatabase(context).siswaData()
        )
    }
}

class AplikasiSiswa : Application() {
    lateinit var container: com.example.myfirstroom.repositori.ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = ContainerDataApp(this)
    }
}