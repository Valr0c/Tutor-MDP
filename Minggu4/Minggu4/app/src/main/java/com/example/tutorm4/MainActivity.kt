package com.example.tutorm4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.example.tutorm4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Lakukan Pengikatan Tampilan. Untuk menggunakan pengikatan tampilan, Anda harus
        // mengaktifkan fitur tersebut di dalam file gradle bernama "build.gradle.kts" untuk Modul
        // Aplikasi dan bukan untuk Proyek.
        //
        // Setelah membuka file tersebut, masukkan fitur build pada lingkup "android" seperti
        // berikut:
        // ```
        //  android {
        //      buildFeatures {
        //          viewBinding = true
        //      }
        // }
        // ```
        //
        // Setelah berhasil disisipkan, sinkronkan file gradle dan kemudian Anda dapat mulai
        // menggunakan fitur View fitur Binding.
        //
        // Ketika menggunakan view binding, Anda perlu memanggil method dari class yang dibuat yang
        // mirip dengan file layout Resource yang ingin kita gunakan, dalam hal ini file layout
        // yang akan kita gunakan adalah "activity_main.xml", dan class Binding yang dihasilkan
        // diberi nama "ActivityMainBinding".
        //
        // Method yang akan kita panggil bernama "inflate()", dan memiliki parameter bertipe
        // LayoutInflater, dalam hal ini "MainActivity" kita memiliki satu. Kita cukup mendapatkan
        // pengambil dari activity's LayoutInflater dengan "layoutInflater" dan mengopernya sebagai
        // argumen dari "inflate()", seperti kode di bawah ini.
        //
        // Hasil pemanggilan metode "inflate()" akan mengembalikan instance objek Binding yang
        // yang dapat kita gunakan untuk mengakses tampilan dalam file layout Resource yang
        // ditargetkan. Namun sebelum kita dapat melakukan itu, kita perlu memastikan bahwa Activity
        // mengetahui apa yang harus dimuat dalam antarmuka penggunanya, dan kita dapat menggunakan
        // instance Binding yang telah di-instansiasi untuknya, dengan memanggil fungsi
        // "setContentView()" dan mengoper properti root instance binding sebagai argumen.
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Kita dapat mengakses view dengan id yang telah kita tetapkan di file sumber daya
        // menggunakan atribut binding instance seperti halnya mengakses properti kelas. Pada contoh
        // di bawah ini adalah bagaimana kita bisa menetapkan OnClickListener ke Button dengan id
        // "loginButton" di "activity_main.xml" layout Resource file.
        binding.loginButton.setOnClickListener {
            if (binding.usernameEditText.text?.trim()?.isEmpty() ?: true == true) {
                Toast.makeText(
                    this@MainActivity,
                    "Masukan username anda!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            openTodoActivity(binding.usernameEditText.text.toString())
        }
    }

    private fun openTodoActivity(username: String) {
        val targetIntent = Intent(this@MainActivity, TodoActivity::class.java)
        targetIntent.putExtra("username", username)
        startActivity(targetIntent)
    }
}