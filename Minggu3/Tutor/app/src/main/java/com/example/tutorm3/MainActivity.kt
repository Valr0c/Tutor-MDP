package com.example.tutorm3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    // Deklarasi semua komponen yang akan dipakai
    private lateinit var txtNama: TextView
    private lateinit var txtNRP: TextView
    private lateinit var txtJurusan: TextView
    private lateinit var txtHasil: TextView
    private lateinit var btnSubmit: Button
    private lateinit var btnPindah: Button
    private lateinit var chkAktif: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ambil semua komponen yang sudah dideklarasi dan simpan dalam variabel
        txtNama = findViewById(R.id.txtNama)
        txtNRP = findViewById(R.id.txtNRP)
        txtJurusan = findViewById(R.id.txtJurusan)
        txtHasil = findViewById(R.id.txtHasil)
        btnSubmit = findViewById(R.id.btnSubmit)
        btnPindah = findViewById(R.id.btnPindah)
        chkAktif = findViewById(R.id.chkAktif)

        // Tambahkan event onclick pada button
        btnSubmit.setOnClickListener(){
            var nama = txtNama.text.toString();
            var nrp = txtNRP.text.toString();
            var jurusan = txtJurusan.text.toString();
            var status = if (chkAktif.isChecked) "Aktif" else "Non-aktif"
            if (nama == "" || nrp == "" || jurusan == ""){
                // Menampilkan toast (notifikasi sederhana) kepada user
                Toast.makeText(this@MainActivity, "Pastikan semua field terisi!", Toast.LENGTH_LONG).show()
                // Toast.makeText(context, message, durasi).show()
                // Jangan lupa .show(), kalau lupa maka toast tidak akan muncul
                // Toast.LENGTH_LONG bisa diganti dengan Toast.LENG_SHORT untuk mengganti durasi toast ditampilkan
            }
            else{
                // concate text & variabel
                var hasil = "Mahasiswa bernama " + nama + " dengan NRP " + nrp + " dari jurusan " + jurusan + " berstatus " + status
                // Random angka, jangan lupa "import kotlin.random.Random"
                // from - until (until tidak termasuk)
                var rnd = Random(System.currentTimeMillis())
                var rndAngka = rnd.nextInt(0, 10)
                hasil = rndAngka.toString() + ". " + hasil

                // Cara lain random lbh pendek
                // var rnd2 = (0..10).random()

                // set txtHasil value
                txtHasil.text = hasil
            }
        }

        btnPindah.setOnClickListener(){
            // intent berfungsi untuk mempersiapkan activity lain yang akan dipanggil
            val intent = Intent(this, KampusActivity::class.java)
            // Intent(activity asal, activity tujuan)
            // startActivity untuk menampilkan
            startActivity(intent)
        }
    }
}