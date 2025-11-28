package com.example.myfirstroom.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.myfirstroom.repositori.RepositoriSiswa

class EntryViewModel(private val repositoriSiswa: RepositoriSiswa) : ViewModel() {
    /**
     * Berisi status Siswa saat ini
     */
    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    /**
     * Fungsi untuk memvalidasi input
     */
    private fun validasiInput(uiState: DetailSiswa = uiStateSiswa.detailSiswa): Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }

    fun updateUiState(detailSiswa: DetailSiswa) {
        uiStateSiswa = UIStateSiswa(
            detailSiswa = detailSiswa,
            isEntryValid = validasiInput(detailSiswa)
        )
    }


    /**
     * Fungsi untuk menyimpan data yang di-entry
     */
    suspend fun saveSiswa() {
        if (validasiInput()) {
            repositoriSiswa.insertSiswa(uiStateSiswa.detailSiswa.toSiswa())
        }
    }
}

// --- Data Classes & Extension Functions ---

data class UIStateSiswa(
    val detailSiswa: DetailSiswa = DetailSiswa(),
    val isEntryValid: Boolean = false
)

data class DetailSiswa(
    val id: Int = 0,
    val nama: String = "",
    val alamat: String = "",
    val telpon: String = ""
)

/**
* Fungsi untuk mengkonversi data input ke data dalam tabel sesuai jenis datanya
*/
fun com.example.myfirstroom.viewmodel.DetailSiswa.toSiswa(): Siswa = Siswa(
    id = id,
    nama = nama,
    alamat = alamat,
    telpon = telpon
)