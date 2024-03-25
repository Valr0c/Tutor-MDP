package com.example.tutorm5

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//Parcelize digunakan untuk mengirimkan object dalam arguments
@Parcelize
data class Mahasiswa(
    val nrp: String,
    var nama: String,
    var jurusan: Int
):Parcelable