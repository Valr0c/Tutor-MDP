package com.example.tutorminggu6

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tutorminggu6.MainActivity.Companion.toRupiah

/**
 * Untuk membuat sebuah ViewModel, Buatlah sebuah class
 * Lalu turunkan class tersebut dengan ViewModel.
 *
 * ViewModel akan berisi berbagai macam logic
 * Dalam ViewModel, kita bisa membuat variabel dan juga method
 *
 * ViewModel akan dipanggil pada activity untuk menjalankan berbagai logic
 */
class HitungViewModel : ViewModel(){
    var biaya:Int = 0
    val hargaPerM2 = 500_000

    fun hitungBiaya(panjang:Int, lebar:Int){
        val luas = panjang * lebar
        biaya = hargaPerM2 * luas
    }
}