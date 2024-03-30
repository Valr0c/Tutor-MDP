package com.example.tutorminggu6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.tutorminggu6.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    /**
     * ViewModel digunakan untuk memisahkan logic business dan view
     * ViewModel akan berjalan di Thread yang berbeda dengan Activity
     *
     * Hal ini berguna agar data tidak hilang saat terjadi perubahan konfigurasi
     * Data yang disimpan pada dapat hilang saat perubahan konfigurasi
     *
     * Buatlah terlebih dulu sebuah class dan turunkan dari class ViewModel
     * Untuk menggunakan ViewModel, buatlah sebuah variabel dengan tipe ViewModel yang baru dibuat
     * Lalu ambil viewModel menggunakan "by viewModels"
     * Dengan perintah diatas, viewModel dapat diakses
     *
     * ViewModel yang dibuat akan mengikuti context Activity yang memanggil
     * ViewModel tidak akan terhapus sampai Activity contextnya menjalankan difinish(onDestroy)
     */
    private val vm:HitungViewModel by viewModels()

    /**
     * Untuk menggunakan Data Binding, buatlah sebuah variabel dataBinding dengan tipe <NamaActivity>.Binding
     * Untuk bisa mengaktifkan DataBinding, tambahkan tag <layout> pada Layout XML.
     * tag <layout> diberikan pada paling luar Layout utama (sebagai wrapper)
     * contoh:
     *
     * <layout>
     *     <ConstraintLayout>
     *          ...
     *     </ConstraintLayout>
     * </layout>
     *
     * Setelah itu, panggillah DataBinding menggunakan DataBindingUtil lalu setContentView.
     */
    private lateinit var binding:ActivityMainBinding

    /* Data untuk coba pembuktian */
    var dataCoba = 0

    /* Disini adalah sebuah variabel yang akan digunakan untuk Data Binding */
    var totalSetelahPajak = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)

        /**
         * setContentView akan berubah menjadi seperti ini untuk mengaktifkan Data Binding
         */
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        /**
         * Pada saat HP di rotate, dataCoba akan kembali menjadi 0
         * Hal ini karena adanya perubahan konfigurasi,
         *
         * pada saat perubahan konfigurasi, Activity akan menjalankan onPause lalu kembali
         * menjalankan onResume. Disini Activity akan kembali ke state awal
         */
        binding.btCobaTambah.setOnClickListener {
            dataCoba++
            binding.txtCoba.text = "Nilai: ${dataCoba}"
        }

        //Setting dari awal, agar saat konfigurasi berubah, nilai tetap terlihat
        binding.tvTotal.text = getString(R.string.total_biaya, vm.biaya.toRupiah())
        binding.btHitung.setOnClickListener {
            val p = binding.etPanjang.text.toString().toInt()
            val l = binding.etLebar.text.toString().toInt()
            vm.hitungBiaya(p,l)
            totalSetelahPajak = (vm.biaya + vm.biaya * 0.1).roundToInt()

            //Update view
            binding.tvTotal.text = getString(R.string.total_biaya, vm.biaya.toRupiah())
            binding.totalAkhir = totalSetelahPajak
            /**
             * Update seperti ini dapat dihilangkan dengan menggunakan LiveData
             * (Buka Activity Berikutnya)
             */
        }

        /**
         * Selain melakukan update secara manual
         * Kita juga dapat langsung menghubungkan data pada layout XML
         *
         * Pada activity_main.xml, kita bisa membuat sebuah variable dengan membuat tag <data>
         * di dalam <layout>
         *
         * <layout>
         *     <data>
         *         <item name="namaVariable" type="tipeVariabel" />
         *     </data>
         *     <ConstraintLayout>
         *         ...
         *     </ConstraintLayout>
         * </layout>
         *
         * Tipe Variabel sama dengan umumnya, namnun untuk class yang kita buat,
         * pemanggilannya perlu mengakses Package,
         * contoh: com.example.tutorminggu6.DataAkhir
         *
         * Setelah itu kita dapat mengakses variable tersebut melalui binding.<namavariabel>
         */
        binding.totalAkhir = totalSetelahPajak

        binding.btPindah.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }

    /**
     *  Apabila kita ingin menambahkan fungsi baru pada class bawaan,
     *  tambahkan sebuah fun pada companion object dengan memanggil
     *  <NamaClass>.<namaFunction>, lalu isilah fungsi tersebut.
     */
    companion object{
        fun Int.toRupiah():String {
            val locale = Locale("id", "ID")
            val rupiahFormatter = NumberFormat.getCurrencyInstance(locale)
            return rupiahFormatter.format(this).toString()
        }
    }
}